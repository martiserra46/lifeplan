package com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.martiserramolina.lifeplan.repository.room.Day

abstract class ItemDiffCallback<T> : DiffUtil.ItemCallback<T>()