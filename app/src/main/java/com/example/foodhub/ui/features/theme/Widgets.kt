package com.example.foodhub.ui.features.theme

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.foodhub.R

@Composable
fun GroupSocialButtons(
    color: Color = Color.White,
    onFacebookClick: () -> Unit,
    onGoogleClick: () -> Unit
    ) {
   Column {

       Row (
           modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
       ){
           HorizontalDivider(
               modifier = Modifier.weight(1f)
                   .padding(horizontal = 16.dp),
               thickness = 1.dp,
                color = color
           )
           Text(text = stringResource(id = R.string.sign_in_with), color = color, modifier = Modifier.padding(horizontal = 8.dp))
              HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                thickness = 1.dp,
                color = color
              )
       }
       Row (
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
         ){
              SocialButtons(
                icon = R.drawable.ic_facebook,
                title = R.string.sign_with_facebook,
                onFacebookClick
              )
              Spacer(modifier = Modifier.size(16.dp))
              SocialButtons(
                icon = R.drawable.ic_google,
                title = R.string.sign_with_google,
                onGoogleClick
              )
         }

   }
}

//Socialbuttons
@Composable
fun SocialButtons(
    icon: Int,title:Int,onClick:()->Unit)
{
    Button(onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = RoundedCornerShape(32.dp)
    ) {
        Row(
            modifier = Modifier.height(38.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(id = title),
                color = Color.Black
            )
        }
    }
}

@Composable
fun FoodHubTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors().copy(
        focusedIndicatorColor = Orange,
        unfocusedIndicatorColor = Color.LightGray.copy(alpha = 0.4f),

    )
){
    Column(Modifier.padding(vertical = 8.dp)) {
        label?.let{
            Row{
                Spacer(modifier = Modifier.size(4.dp))
                it()
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange,
            modifier,
            enabled,
            readOnly,
            textStyle.copy(fontWeight = FontWeight.SemiBold),
            null,
            placeholder,
            leadingIcon,
            trailingIcon,
            prefix,
            suffix,
            supportingText,
            isError,
            visualTransformation,
            keyboardOptions,
            keyboardActions,
            singleLine,
            maxLines,
            minLines,
            interactionSource,
            shape,
            colors
        )
    }
}