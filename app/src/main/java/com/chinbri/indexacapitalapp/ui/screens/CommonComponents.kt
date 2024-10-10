package com.chinbri.indexacapitalapp.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.chinbri.indexacapitalapp.ui.theme.DarkBlue

@Composable
fun HyperlinkText(
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    fontStyle: FontStyle = FontStyle.Normal,
    fontSize: TextUnit = 16.sp,
    color: Color = Color.DarkGray,
) {
    val context = LocalContext.current
    val annotatedString = remember {
        buildAnnotatedString {
            val regex = Regex("""<a\s+href=["'](.*?)["']>(.*?)</a>""")
            var lastIndex = 0
            regex.findAll(text).forEach { matchResult ->
                val (url, linkText) = matchResult.destructured
                append(text.substring(lastIndex, matchResult.range.first))
                pushStringAnnotation(tag = "URL", annotation = url)
                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append(linkText)
                }
                pop()
                lastIndex = matchResult.range.last + 1
            }
            append(text.substring(lastIndex))
        }
    }

    ClickableText(
        text = annotatedString,
        style = androidx.compose.ui.text.TextStyle(
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            fontSize = fontSize,
            color = color
        ),
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                    context.startActivity(intent)
                }
        }
    )

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun IndexaText(
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    fontStyle: FontStyle = FontStyle.Normal,
    fontSize: TextUnit = 16.sp,
    color: Color = Color.DarkGray,
    maxLines: Int = 1,
    expandable: Boolean = false
) {

    var expanded by remember { mutableStateOf(false) }

    Text(
        modifier = if (expandable) {
            modifier.clickable {
                expanded = !expanded
            }
        } else modifier,
        text = text,
        fontWeight = fontWeight,
        fontStyle = fontStyle,
        fontSize = fontSize,
        color = color,
        maxLines = if (expanded) Int.MAX_VALUE else maxLines
    )
}

@Composable
fun IndexaButton(text: String, onClick: () -> Unit) {

    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)) {
        Text(text = text, fontSize = 16.sp)
    }

}

@Preview
@Composable
fun IndexaButtonPreview() {
    IndexaButton("Close") {}
}