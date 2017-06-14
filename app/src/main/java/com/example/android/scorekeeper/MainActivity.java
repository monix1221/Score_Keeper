package com.example.android.scorekeeper;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android.scorekeeper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding mBinding;

    private int mScoreForTeamA = 0;
    private int mScoreForTeamB = 0;

    private String TEAM_A_NAME = "teamA";
    private String TEAM_A_SCORE = "teamASc";
    private String TEAM_B_NAME = "teamB";
    private String TEAM_B_SCORE = "teamBSc";
    private String SCORE_FOR_A_VALUE = "scForA";
    private String SCORE_FOR_B_VALUE = "scForB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (savedInstanceState != null) {
            mBinding.teamAScore.setText(savedInstanceState.getString(TEAM_A_SCORE));
            mBinding.teamBScore.setText(savedInstanceState.getString(TEAM_B_SCORE));
            mBinding.teamATv.setText(savedInstanceState.getString(TEAM_A_NAME));
            mBinding.teamBTv.setText(savedInstanceState.getString(TEAM_B_NAME));
            mScoreForTeamA = savedInstanceState.getInt(SCORE_FOR_A_VALUE);
            mScoreForTeamB = savedInstanceState.getInt(SCORE_FOR_B_VALUE);
        }

        mBinding.plus3ForA.setOnClickListener(this);
        mBinding.plus3ForB.setOnClickListener(this);
        mBinding.plus5ForA.setOnClickListener(this);
        mBinding.plus5ForB.setOnClickListener(this);
        mBinding.freeKickForA.setOnClickListener(this);
        mBinding.freeKickForB.setOnClickListener(this);
        mBinding.penaltyAButton.setOnClickListener(this);
        mBinding.penaltyBButton.setOnClickListener(this);
        mBinding.resetGameButton.setOnClickListener(this);
        mBinding.teamAScore.setText(String.valueOf(mScoreForTeamA));
        mBinding.teamBScore.setText(String.valueOf(mScoreForTeamB));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEAM_A_NAME, mBinding.teamATv.getText().toString());
        outState.putString(TEAM_A_SCORE, mBinding.teamAScore.getText().toString());
        outState.putString(TEAM_B_NAME, mBinding.teamBTv.getText().toString());
        outState.putString(TEAM_B_SCORE, mBinding.teamBScore.getText().toString());
        outState.putInt(SCORE_FOR_A_VALUE, mScoreForTeamA);
        outState.putInt(SCORE_FOR_B_VALUE, mScoreForTeamB);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mBinding.teamAScore.setText(savedInstanceState.getString(TEAM_A_SCORE));
        mBinding.teamBScore.setText(savedInstanceState.getString(TEAM_B_SCORE));
        mBinding.teamATv.setText(savedInstanceState.getString(TEAM_A_NAME));
        mBinding.teamBTv.setText(savedInstanceState.getString(TEAM_B_NAME));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.free_kick_for_a:
                mScoreForTeamA += 1;
                displayForTeamA(mScoreForTeamA);
                break;
            case R.id.free_kick_for_b:
                mScoreForTeamB += 1;
                displayForTeamB(mScoreForTeamB);
                break;
            case R.id.plus_3_for_a:
                mScoreForTeamA += 3;
                displayForTeamA(mScoreForTeamA);
                break;
            case R.id.plus_3_for_b:
                mScoreForTeamB += 3;
                displayForTeamB(mScoreForTeamB);
                break;
            case R.id.plus_5_for_a:
                mScoreForTeamA += 5;
                displayForTeamA(mScoreForTeamA);
                break;
            case R.id.plus_5_for_b:
                mScoreForTeamB += 5;
                displayForTeamB(mScoreForTeamB);
                break;
            case R.id.penalty_a_button:
                if (mScoreForTeamA == 0) {
                    Toast.makeText(MainActivity.this, R.string.toast_score_below_zero_message, Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    mScoreForTeamA -= 1;
                    displayForTeamA(mScoreForTeamA);
                    break;
                }
            case R.id.penalty_b_button:
                if (mScoreForTeamB == 0) {
                    Toast.makeText(MainActivity.this, R.string.toast_score_below_zero_message, Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    mScoreForTeamB -= 1;
                    displayForTeamB(mScoreForTeamB);
                    break;
                }
            case R.id.reset_game_button:
                mScoreForTeamA=0;
                mScoreForTeamB=0;
                displayForTeamA(mScoreForTeamA);
                displayForTeamB(mScoreForTeamB);
                break;
        }
    }

    private void displayForTeamA(int score) {
        mBinding.teamAScore.setText(String.valueOf(score));
    }

    private void displayForTeamB(int score) {
        mBinding.teamBScore.setText(String.valueOf(score));
    }
}