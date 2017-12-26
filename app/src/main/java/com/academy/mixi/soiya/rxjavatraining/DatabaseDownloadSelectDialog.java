package com.academy.mixi.soiya.rxjavatraining;

import android.app.AlertDialog;
import android.content.DialogInterface;

class DatabaseDownloadSelectDialog {
    public static int selectBtnNumber;

    DatabaseDownloadSelectDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyContext.getInstance().getApplicationContext());
        builder.setTitle("処理を選択して下さい");

        String[] items = {"not RxJava", "just only", "defer", "fromCallable"};
        builder.setSingleChoiceItems(items, 0, mItemListener);

        builder.setPositiveButton("OK", mButtonListener );
        builder.setNeutralButton ("Cancel", mButtonListener );

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private DialogInterface.OnClickListener mItemListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            selectBtnNumber = which;
        }
    };

    private DialogInterface.OnClickListener mButtonListener = (dialog, which) -> {
        String btnStr = "";
        switch( which ){
            case AlertDialog.BUTTON_POSITIVE:
                btnStr = "OK";
                break;
            case AlertDialog.BUTTON_NEUTRAL:
                btnStr = "Cancel";
                break;
        }
    };

}
