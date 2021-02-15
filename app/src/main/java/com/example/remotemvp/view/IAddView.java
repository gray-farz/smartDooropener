package com.example.remotemvp.view;


import com.example.remotemvp.presenter.ListAdapterPresenterCompl;

public interface IAddView
{
    public void setAdapterOfList(ListAdapterPresenterCompl adapter);
    public void showAddLayout();
    public void setTextedtName(String str);
    public void validateTexts();
    public String getEdtName();
    public String getEdtAddress();
}
