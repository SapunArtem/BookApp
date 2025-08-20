package com.example.bookapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.bookapp.R
import com.example.bookapp.presentation.components.profile.EditProfileDialog
import com.example.bookapp.presentation.viewModel.ProfileViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var isEditing by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error != null -> {
                Text(
                    text = state.error ?: stringResource(R.string.error),
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.profile != null -> {
                val profile = state.profile!!

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {

                        GlideImage(
                            model = profile.avatarUrl,
                            contentDescription = "Profile background",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .alpha(0.7f)
                        )
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .border(4.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
                                .background(Color.LightGray)
                        ) {
                            GlideImage(
                                model = profile.avatarUrl,
                                contentDescription = "User avatar",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Имя и email
                    Text(
                        modifier = Modifier.testTag("Name"),
                        text = profile.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold)
                    Text(
                        modifier = Modifier.testTag("Email"),
                        text = profile.email,
                        fontSize = 16.sp,
                        color = Color.Gray)

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        modifier = Modifier.testTag("BtnEditProfile"),
                        onClick = { isEditing = true },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.secondary,
                            containerColor = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        Text(text = stringResource(R.string.edit_profile))
                    }
                }

                // Модальное окно редактирования
                if (isEditing) {
                    EditProfileDialog(
                        profile = profile,
                        onDismiss = { isEditing = false },
                        onSave = { name, email ->
                            viewModel.updateProfile(name, email)
                            isEditing = false
                        }
                    )
                }
            }
        }
    }
}