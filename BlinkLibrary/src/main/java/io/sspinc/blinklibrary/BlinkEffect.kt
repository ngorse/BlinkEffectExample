import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.animation.Animation
import com.snowplowanalytics.snowplow.Snowplow
import com.snowplowanalytics.snowplow.event.ScreenView
import androidx.activity.result.ActivityResultLauncher
import io.sspinc.blinklibrary.Popup

object BlinkEffect {
    private lateinit var launcher: ActivityResultLauncher<Intent>

    fun blink(view: View) {
        val event = ScreenView("Main screen")
        Snowplow.defaultTracker?.track(event)

        // adding the color to be shown
        val animator: ObjectAnimator = ObjectAnimator.ofInt(
            view, "backgroundColor", Color.YELLOW,
            Color.RED, Color.GREEN
        )
        // duration of one color
        animator.duration = 500;
        animator.setEvaluator(ArgbEvaluator())
        // color will be shown in reverse manner
        animator.repeatCount = Animation.REVERSE
        // Repeat up to infinite time
        animator.repeatCount = Animation.INFINITE
        animator.start()

        val intent = Intent(view.context, Popup::class.java)
        launcher.launch(intent)
    }
}
