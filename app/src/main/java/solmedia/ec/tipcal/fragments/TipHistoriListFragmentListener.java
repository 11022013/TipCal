package solmedia.ec.tipcal.fragments;

import solmedia.ec.tipcal.models.TipModel;

public interface TipHistoriListFragmentListener {

    void addTip(TipModel tipModel);

    void clearList();
}
