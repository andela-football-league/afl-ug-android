package com.example.wagubibrian.afl_ug_android.match

import android.app.AlarmManager
import android.app.PendingIntent
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.wagubibrian.afl_ug_android.MyApplication

import com.example.wagubibrian.afl_ug_android.R
import com.example.wagubibrian.afl_ug_android.domain.di.helper.ViewModelFactory
import com.example.wagubibrian.afl_ug_android.matchactivity.MatchActivity
import com.example.wagubibrian.afl_ug_android.domain.data.prefs.PreferencesHelper
import com.example.wagubibrian.afl_ug_android.utils.TimerExpiredReceiver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.match_fragment.*
import java.util.*
import javax.inject.Inject



class MatchFragment : Fragment() {

    companion object {
        fun newInstance() = MatchFragment()

        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long) : Long {
            val wakeUpTime = ( nowSeconds + secondsRemaining ) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context,0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PreferencesHelper.setAlarmSetTime(nowSeconds, context)
            return wakeUpTime
        }

        fun removeAlarm(context: Context) {
            var intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context,0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            PreferencesHelper.setAlarmSetTime(0 , context)
        }

        val nowSeconds: Long
           get() = Calendar.getInstance().timeInMillis / 1000
    }

    private lateinit var viewModel: MatchViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var timer: CountDownTimer

    lateinit var teamA: String

    lateinit var teamB: String

    lateinit var compositeDisposable: CompositeDisposable

    var matchId: Int? = 0

    var status: Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        compositeDisposable = CompositeDisposable()

        MyApplication.component.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MatchViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.match_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamA = arguments?.getString("Team A", "N/A").toString()
        teamB = arguments?.getString("Team B", "N/A").toString()
        matchId = arguments?.getInt("Match id", 0)
        status = arguments?.getInt("status", -1)

        matchId?.let { viewModel.setMatchId(it) }
        status?.let { viewModel.setMatchStatus(it) }

        var teamValueExistsA = teamA.equals("null", true) && viewModel.getHomeTeam() == "N/A"
        var teamValueExistsB = teamB.equals("null", true) && viewModel.getHomeTeam() == "N/A"
        var noArguments = teamA.equals("null", true)

        textView2.text = if (teamValueExistsA) {"N/A"} else viewModel.getHomeTeam()
        textView3.text = if (teamValueExistsB) "N/A" else viewModel.getAwayTeam()

        if (teamValueExistsA && teamValueExistsB) {
            textView2.text = "N/A"
            textView3.text = "N/A"
        } else if (noArguments) {
            textView2.text = viewModel.getHomeTeam()
            textView3.text = viewModel.getAwayTeam()
        } else  {
            textView2.text = teamA
            textView3.text = teamB
        }

        fab_start.setOnClickListener {
            if ( viewModel.getMatchStatus() == 1){
                Toast.makeText(activity, "This match was already played. You cannot perform this action.", Toast.LENGTH_LONG).show()
            } else {
                viewModel.startTimerCount()
                matchId?.let {
                    if (status == -1) {
                        updateMatchDetails(it, 0)
                        activity?.let { it -> PreferencesHelper.setMatchStatus(0, it) }
                    }
                }
                startCountDown()
                updateButtons()
            }
        }

        fab_pause.setOnClickListener{
            if ( viewModel.getMatchStatus() == 1){
                Toast.makeText(activity, "This match was already played. You cannot perform this action.", Toast.LENGTH_LONG).show()
            } else {
                viewModel.pauseTimerCount()
                timer.cancel()
                updateButtons()
            }
        }

        fab_end.setOnClickListener{
            viewModel.endTimerCount()
            timer.cancel()
            onTimerFinished()
            // TODO: Is there a time range where it is valid for a user to end the time and do we reset the game or end it
            matchId?.let { updateMatchDetails(it, -1) }
        }

        var intent = Intent(this.context, MatchActivity::class.java)

        home_team.setOnClickListener{
            registerActivity(intent, textView2.text.toString(), "home")
        }

        away_team.setOnClickListener{
            registerActivity(intent, textView3.text.toString(),"away")
        }

        if ((textView2.text as String).equals("N/A", true))
            disableButtons()
    }

    private fun registerActivity(intent: Intent, teamName: String,value: String) {
        if ((viewModel.secondsRemaining == 60L) || (viewModel.secondsRemaining == 0L)){
            Toast.makeText(activity, "You cannot perform the action. The match hasn't started or was already played.", Toast.LENGTH_LONG).show()
        }else {
            intent.putExtra("team", teamName)
            intent.putExtra("home_away", value)
            startActivity(intent)
        }
    }

    private fun updateMatchScoreCards(matchId: Int) {
        var ob = viewModel
            .getMatch(matchId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    home_team.text = "${it.homeScore}"
                    away_team.text = "${it.awayScore}"
                },
                {
                    Toast.makeText(activity, "An Error has occurred $it", Toast.LENGTH_LONG).show()
                }
            )
        compositeDisposable.add(ob)
    }

    private fun updateMatchDetails(matchId: Int , matchStatus: Int) {
        var observable = viewModel
            .getMatch(matchId)
            .flatMapCompletable {
                it.status = matchStatus
                viewModel.updateMatch(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {},
                {
                    Toast.makeText(activity, "An Error has occurred $it", Toast.LENGTH_LONG).show()
                }
            )
        compositeDisposable.add(observable)

    }

    override fun onResume() {
        super.onResume()
        updateMatchScoreCards(viewModel.getMatchId())
        initTimer()

        // TODO: Notification
        this.context?.let {
            removeAlarm(it)
        }
    }

    override fun onPause() {
        super.onPause()
        setTeamDetails()
        when (viewModel.timerState) {
            MatchViewModel.TimerState.Running -> {
                timer.cancel()

                this.context?.let {
                    val wakeUpTime = setAlarm(it, nowSeconds, viewModel.secondsRemaining)
                }

                // TODO: Notification
            }
            else -> {
                //
            }
        }
        viewModel.onPauseInvoke()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun initTimer() {
        var timerState = viewModel.getTimerStatePref()

        when (timerState) {
            MatchViewModel.TimerState.Stopped -> {
                viewModel.setNewTimerLength()
                var length = viewModel.getNewTimerLength()
                materialProgressBar.max = length.toInt()
                //Update Match when the timer runs out to finished.
                if (viewModel.getMatchStatus() == 1) {
                    matchId?.let { updateMatchDetails(it, 1) }
                }
            }
            else -> {
                viewModel.setNewTimerLength()
                var length = viewModel.getNewTimerLength()
                materialProgressBar.max = length.toInt()
//                viewModel.setPreviousTimerLength()
            }
        }
        viewModel.setSecondsRemaining()

        val alarmSetTime = this.context?.let { PreferencesHelper.getAlarmSetTime(it) }
        if (alarmSetTime != null) {
            if (alarmSetTime > 0) {
                viewModel.secondsRemaining -= nowSeconds - alarmSetTime
            }
        }

        if (viewModel.secondsRemaining <= 0) {
            onTimerFinished()
        }
        else if (timerState == MatchViewModel.TimerState.Running) {
            viewModel.startTimerCount()
            startCountDown()
        }

        updateButtons()
        updateCountDownUi()
    }

    private fun startCountDown() {
        timer = object: CountDownTimer(viewModel.secondsRemaining * 1000, 1000) {
            override fun onFinish() {
                viewModel.onTimerFinish()
                viewModel.setMatchStatus(1)
                matchId?.let { updateMatchDetails(it, 1) }
                onTimerFinished()
            }

            override fun onTick(millisUntilFinished: Long){
                viewModel.secondsRemaining = millisUntilFinished/ 1000
                updateCountDownUi()
            }
        }.start()
    }

    private fun onTimerFinished() {
        materialProgressBar.progress = 0

        updateButtons()
        updateCountDownUi()
    }

    private fun updateButtons() {
        when(viewModel.timerState){
             MatchViewModel.TimerState.Running -> {
                 fab_start.isEnabled = false
                 fab_pause.isEnabled = true
                 fab_end.isEnabled = true
             }
             MatchViewModel.TimerState.Paused -> {
                 fab_start.isEnabled = true
                 fab_pause.isEnabled = false
                 fab_end.isEnabled = true
             }
             MatchViewModel.TimerState.Stopped -> {
                 fab_start.isEnabled = true
                 fab_pause.isEnabled = true
                 fab_end.isEnabled = false
             }
        }
    }

    private fun disableButtons() {
        fab_start.isClickable = false
        fab_pause.isClickable = false
        fab_end.isClickable = false
        home_team.isClickable = false
        away_team.isClickable = false
    }


    private fun updateCountDownUi() {
        var minutesUntilFinished = viewModel.secondsRemaining / 60
        var secondsInMinutesUntilFinished = viewModel.secondsRemaining - minutesUntilFinished * 60
        val secondStr = secondsInMinutesUntilFinished.toString()
        text_view_countdown.text = "$minutesUntilFinished:${if (secondStr.length == 2) secondStr else "0$secondStr"}"
        materialProgressBar.progress = (viewModel.timerLengthSeconds - viewModel.secondsRemaining).toInt()
    }

    private fun setTeamDetails() {
        if (teamA != "null") {
            viewModel.setHomeTeam(teamA)
            viewModel.setAwayTeam(teamB)
        }
    }

}
