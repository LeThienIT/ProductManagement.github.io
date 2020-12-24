
package ql_sanpham.entity;

/*
 *@Author: AnthonyLe
 * *Vjp pRo
 */

public class Event {
    private int idEvent;
    private String nameEvent;

    public Event(int idEvent, String nameEvent) {
        this.idEvent = idEvent;
        this.nameEvent = nameEvent;
    }

    public Event() {
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    @Override
    public String toString() {
        return "Event{" + "idEvent=" + idEvent + ", nameEvent=" + nameEvent + '}';
    }
    
    
    
}
