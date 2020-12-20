/*
 * Copyright 2018-present KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kunminx.puremusic.ui.page.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.kunminx.puremusic.R;
import com.kunminx.puremusic.data.bean.TestAlbum;
import com.kunminx.puremusic.databinding.AdapterPlayItemBinding;
import com.kunminx.puremusic.player.PlayerManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Create by KunMinX at 20/4/19
 */
public class PlaylistAdapter extends BaseQuickAdapter<TestAlbum.TestMusic, BaseDataBindingHolder> {

    public PlaylistAdapter() {
        super(R.layout.adapter_play_item, new ArrayList<>());
    }


    @Override
    protected void convert(@NotNull BaseDataBindingHolder dataBindBaseViewHolder, TestAlbum.TestMusic testMusic) {
        AdapterPlayItemBinding binding = (AdapterPlayItemBinding) dataBindBaseViewHolder.getDataBinding();
        binding.executePendingBindings();
        binding.setAlbum(testMusic);
        int currentIndex = PlayerManager.getInstance().getAlbumIndex();
//        binding.ivPlayStatus.setColor(currentIndex == holder.getAbsoluteAdapterPosition()
//                ? binding.getRoot().getContext().getColor(R.color.gray) : Color.TRANSPARENT);
    }
}
