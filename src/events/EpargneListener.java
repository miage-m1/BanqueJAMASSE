package events;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EpargneListener implements PropertyChangeListener {

    final private Context context;

    public EpargneListener(Context context) {
        this.context = context;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
