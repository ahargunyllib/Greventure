package com.seven_sheesh.greventure.presentation.ui.widget.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

data class FAQItem(
    val title: String,
    val content: String
)

@Composable
fun Accordion(faqItems: List<FAQItem> = predefinedFAQs) {
    Column {
        faqItems.forEach { faq ->
            var expanded by remember { mutableStateOf(false) }
            val degrees by animateFloatAsState(if (expanded) -90f else 90f)
            Column {
                Row(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { expanded = expanded.not() }
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicText(faq.title, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.fillMaxWidth(0.9f))
                    Image(
                        Icons.Rounded.ChevronRight,
                        contentDescription = null,
                        modifier = Modifier.rotate(degrees).size(24.dp)
                    )
                }
                AnimatedVisibility(
                    visible = expanded,
                    enter = expandVertically(
                        spring(
                            stiffness = Spring.StiffnessMediumLow,
                            visibilityThreshold = IntSize.VisibilityThreshold
                        )
                    ),
                    exit = shrinkVertically()
                ) {
                    Box(Modifier.fillMaxWidth().padding(16.dp)) {
                        BasicText(
                            faq.content,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Box(Modifier.fillMaxWidth().height(1.dp).background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)))
            }
        }
    }
}
private val predefinedFAQs = listOf(
    FAQItem(
        title = "Bagaimana cara mendaftar untuk kegiatan ini?",
        content = "Anda dapat mendaftar untuk kegiatan ini melalui media sosial kami atau dengan menghubungi panitia penyelenggara secara langsung."
    ),
    FAQItem(
        title = "Bagaimana jika saya tidak bisa hadir di seluruh durasi kegiatan?",
        content = "Anda bebas untuk menghadiri kegiatan sesuai dengan waktu luang Anda. Namun, kami sarankan untuk hadir di sesi pembukaan dan penutupan agar tidak ketinggalan informasi penting."
    ),
    FAQItem(
        title = "Apakah saya bisa membawa teman atau keluarga?",
        content = "Tentu saja! Kami mendorong Anda untuk mengajak teman dan keluarga untuk bergabung dan meramaikan acara ini."
    )
)

