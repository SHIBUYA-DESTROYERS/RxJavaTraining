package com.academy.mixi.soiya.rxjavatraining;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button actionButton;
    private Button selectButton;
    private TextView resultTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyContext.onCreateApplication(MainActivity.this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        actionButton = (Button) findViewById(R.id.btn_action);
        selectButton = (Button) findViewById(R.id.btn_dialog_select);
        resultTextView = (TextView) findViewById(R.id.result);

        btnActionClick();
        btnSelectClick();

    }

    private void btnActionClick(){
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (DatabaseDownloadSelectDialog.selectBtnNumber) {
                    // not RxJava
                    case 0:
                        showProgress();
                        try {
                            resultTextView.setText(Database.readValue());
                        } catch (IOException e) {
                            resultTextView.setText(e.getMessage());
                        }

                        hideProgress();
                        break;

                    // just only
                    case 1:
                        showProgress();
                        try {
                            Observable.just(Database.readValue())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(value -> {
                                            resultTextView.setText(value);
                                            hideProgress();
                                    });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    // defer
                    case 2:
                        showProgress();
                        Observable<String> deferred = Observable.defer(() -> {
                            try {
                                return Observable.just(Database.readValue());
                            } catch (IOException e) {
                                return Observable.error(e);
                            }
                        });

                        deferred.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(value -> {
                                    resultTextView.setText(value);
                                    hideProgress();
                                });
                        break;

                    // fromCallable
                    case 3:
                        showProgress();
                        Observable<String> fromCallable = Observable.fromCallable(Database::readValue);

                        fromCallable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(value -> {
                                    resultTextView.setText(value);
                                    hideProgress();
                                });
                }
            }
        });
    }

    private void btnSelectClick(){
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseDownloadSelectDialog dialog =
                        new DatabaseDownloadSelectDialog();
            }
        });
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }


}
