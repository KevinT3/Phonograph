package com.kabouzeid.gramophone.interfaces;

import com.afollestad.materialcab.MaterialCab;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */
public interface CabHolder {

    MaterialCab openCab(final int menuRes, final MaterialCab.Callback callback);
}
