package com.SOCIETIES.Framework;

import java.util.ArrayList;

public interface IRepo
{
    ArrayList<CVersionControlPost> GetChanges();

    ArrayList<CVersionControlPost> GetChanges(String UserID);
}
