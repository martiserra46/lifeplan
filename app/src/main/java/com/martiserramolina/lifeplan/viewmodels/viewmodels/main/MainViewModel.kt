package com.martiserramolina.lifeplan.viewmodels.viewmodels.main

import androidx.lifecycle.ViewModel
import com.martiserramolina.lifeplan.utils.enums.NavSection

class MainViewModel(
    var navSection: NavSection
) : ViewModel()