package be.eaict.blackboardsnapshotapp.Objects

import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.provider.MediaStore
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import org.jetbrains.anko.startActivity
import java.lang.Exception
import java.lang.ref.WeakReference

class SaveImageHelper : Target {

    private var context: Context
    private var alertDialogWeakReference : WeakReference<AlertDialog>
    private var contentResolverWeakReference: WeakReference<ContentResolver>
    private var name : String
    private var desc : String

    constructor(context: Context, alertDialog: AlertDialog, contentResolver: ContentResolver, name: String, desc: String) {
        this.context = context
        this.alertDialogWeakReference = WeakReference<AlertDialog>(alertDialog)
        this.contentResolverWeakReference = WeakReference<ContentResolver>(contentResolver)
        this.name = name
        this.desc = desc
    }


    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        val r : ContentResolver? = contentResolverWeakReference.get()
        val dialog : AlertDialog? = alertDialogWeakReference.get()
        if (r != null){
            MediaStore.Images.Media.insertImage(r, bitmap, name, desc)
        }
        dialog?.dismiss()

        //Open gallery after download
        val intent: Intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        context.startActivity(Intent.createChooser(intent, "VIEW PICTURE"))
        }
}