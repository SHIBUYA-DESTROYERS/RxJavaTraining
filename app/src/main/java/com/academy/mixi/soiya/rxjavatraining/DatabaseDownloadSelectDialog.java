package com.academy.mixi.soiya.rxjavatraining;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

class DatabaseDownloadSelectDialog {
    public static int selectBtnNumber;

    DatabaseDownloadSelectDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyContext.getInstance().getApplicationContext());
        builder.setTitle("ラジオボタンダイアログ");

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
            Toast.makeText(MyContext.getInstance().getApplicationContext(), "Item " + which + " clicked.", Toast.LENGTH_SHORT).show();
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
