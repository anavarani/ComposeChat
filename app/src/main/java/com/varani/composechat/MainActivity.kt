package com.varani.composechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.varani.composechat.ui.theme.ComposeChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChatTheme {
                ComposeChatApp(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun ComposeChatApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = { ChatTopBar() },
        bottomBar = { ChatMessageBar() }
    ) { innerPadding ->
        ChatSection(Modifier.padding(innerPadding))
    }
}

@Composable
fun ChatTopBar() {
}

@Composable
fun ChatMessageBar() {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high),
                unfocusedBorderColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.disabled),
                trailingIconColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high),
                disabledTrailingIconColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.disabled),
                textColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
                disabledTextColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.disabled),
            ),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(18.dp))
        Button(
            onClick = { },
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            ),
            modifier = Modifier
                .size(48.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.send_icon),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun ChatSection(modifier: Modifier) {

}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun DefaultPreview() {
    ComposeChatTheme {
        ComposeChatApp(Modifier.fillMaxSize())
    }
}