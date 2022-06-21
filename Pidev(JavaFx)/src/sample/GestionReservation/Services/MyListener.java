package sample.GestionReservation.Services;

import sample.GestionOffre.models.Offre;
import sample.GestionReservation.model.*;

public interface MyListener {
    void onClickListener(Vol vol);
    void onClickListener2(Transport transport);
    void onClickk(Hotel hotel);
    void onClickk2(Offre offre);
    void onClickk3(Event event);
}
