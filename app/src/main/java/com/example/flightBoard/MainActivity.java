package com.example.flightBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.flightBoard.databinding.ActivityMainBinding;
import com.example.flightBoard.utilities.FakeDataUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        BoardingPassInfo fakeBoardingInfo = FakeDataUtils.generateFakeBoardingPassInfo();
        displayBoardingPassInfo(fakeBoardingInfo);
    }

    private void displayBoardingPassInfo(BoardingPassInfo info) {

        mBinding.textViewPassengerName.setText(info.passengerName);
        mBinding.flightInfo.textViewOriginAirport.setText(info.originCode);
        mBinding.flightInfo.textViewFlightCode.setText(info.flightCode);
        mBinding.flightInfo.textViewDestinationAirport.setText(info.destCode);

        SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault());
        String boardingTime = formatter.format(info.boardingTime);
        String departureTime = formatter.format(info.departureTime);
        String arrivalTime = formatter.format(info.arrivalTime);

        mBinding.textViewBoardingTime.setText(boardingTime);
        mBinding.textViewDepartureTime.setText(departureTime);
        mBinding.textViewArrivalTime.setText(arrivalTime);

        long totalMinutesUntilBoarding = info.getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
        long minutesLessHoursUntilBoarding =
                totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);

        String hoursAndMinutesUntilBoarding = getString(R.string.countDownFormat,
                hoursUntilBoarding,
                minutesLessHoursUntilBoarding);

        mBinding.textViewBoardingInCountdown.setText(hoursAndMinutesUntilBoarding);
        // COMPLETED (8) Use the boardingInfo attribute in mBinding below to get the appropriate text Views
        mBinding.boardingInfo.textViewTerminal.setText(info.departureTerminal);
        mBinding.boardingInfo.textViewGate.setText(info.departureGate);
        mBinding.boardingInfo.textViewSeat.setText(info.seatNumber);
    }
}