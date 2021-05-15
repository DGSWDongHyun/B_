package com.simple.b_.view.fragments

import com.simple.b_.R
import com.simple.b_.base.BaseFragment
import com.simple.b_.databinding.FragmentChatBinding
import com.simple.b_.viewmodel.chat.ChatViewModel


class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>() {
    override val viewModel: ChatViewModel
        get() = ChatViewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_chat

}