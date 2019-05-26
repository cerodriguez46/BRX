package christopher.brx

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("imageUrl")
// ? will return string imgUrl as null if null
fun bindImage(imgView: ImageView, imgUrl: String?) {

    //let takes object it is invoked upon as a parameter and returns the result of the lambda expression
    //let is a scoping function where variables declared within the function cannot be declared outside
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("listData")
// ? will return data List from StroesPropertyX as null if null
fun bindRecyclerView(recyclerView: RecyclerView, data: List<StoresPropertyX>?) {
    val adapter = recyclerView.adapter as StoreGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("storesApiStatus")
// ? will return status from StoresApiStatus as null if null
fun bindStatus(statusImageView: ImageView, status: StoresApiStatus?) {
    when (status) {
        StoresApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        StoresApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        StoresApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }

    //Ctrl+Q looks up documentation
}