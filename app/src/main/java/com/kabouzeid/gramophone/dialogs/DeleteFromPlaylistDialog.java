package com.kabouzeid.gramophone.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Html;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kabouzeid.gramophone.R;
import com.kabouzeid.gramophone.model.PlaylistSong;
import com.kabouzeid.gramophone.util.PlaylistsUtil;

import java.util.ArrayList;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */
public class DeleteFromPlaylistDialog extends DialogFragment {

    public static DeleteFromPlaylistDialog create(PlaylistSong song) {
        ArrayList<PlaylistSong> list = new ArrayList<>();
        list.add(song);
        return create(list);
    }

    public static DeleteFromPlaylistDialog create(ArrayList<PlaylistSong> songs) {
        DeleteFromPlaylistDialog dialog = new DeleteFromPlaylistDialog();
        Bundle args = new Bundle();
        args.putSerializable("songs", songs);
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //noinspection unchecked
        final ArrayList<PlaylistSong> songs = (ArrayList<PlaylistSong>) getArguments().getSerializable("songs");
        int title;
        CharSequence content;
        if (songs.size() > 1) {
            title = R.string.delete_songs_from_playlist_title;
            content = Html.fromHtml(getString(R.string.delete_x_songs_from_playlist, songs.size()));
        } else {
            title = R.string.delete_song_from_playlist_title;
            content = Html.fromHtml(getString(R.string.delete_song_x_from_playlist, songs.get(0).title));
        }
        return new MaterialDialog.Builder(getActivity())
                .title(title)
                .content(content)
                .positiveText(R.string.delete_action)
                .negativeText(android.R.string.cancel)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        if (getActivity() == null)
                            return;
                        PlaylistsUtil.removeFromPlaylist(getActivity(), songs);
                    }
                }).build();
    }
}