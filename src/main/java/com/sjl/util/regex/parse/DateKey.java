package com.sjl.util.regex.parse;

import java.text.*;
import java.util.*;

public class DateKey extends Key<Date>
{
    private DateFormat format;
    
    public DateKey(String aName, String aFormat)
    {
        super(aName);
        format = new SimpleDateFormat(aFormat);
    }
    
    @Override
    protected Date convert(String aString)
    {
        try
        {
            return format.parse(aString);
        }
        catch (ParseException anExc)
        {
            throw new RuntimeException(anExc);
        }
    }

}
