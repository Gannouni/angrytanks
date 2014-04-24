package be.kdg.angrytanks.dom.exceptions;

/**
 * Alexander Gannouni & Bert Willekens
 * Date: 08/02/14
 */
public class AngryTanksException extends Exception {

    public AngryTanksException()
    {
    }

    public AngryTanksException(String message)
    {
        super(message);
    }

    public AngryTanksException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public AngryTanksException(Throwable cause)
    {
        super(cause);
    }

    public AngryTanksException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
