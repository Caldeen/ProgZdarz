package Start;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="Route")
public class Route implements Serializable {
    public Route(int id, String routeStart, String routeEnd, int driverID, float time) {
        this.id = id;
        this.routeStart = routeStart;
        this.routeEnd = routeEnd;
        this.driverID = driverID;
        this.time = time;
    }

    @Id
    @Column(name = "routeID",unique = true,nullable = false)
    private int id;

    @Id
    @Column(name = "routeStart")
    private String routeStart;

    @Id
    @Column(name = "routeEnd")
    private String routeEnd;

    @Id
    @Column(name = "driverID")
    private int driverID;

    @Id
    @Column(name = "time")
    private float time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRouteStart() {
        return routeStart;
    }

    public void setRouteStart(String routeStart) {
        this.routeStart = routeStart;
    }

    public String getRouteEnd() {
        return routeEnd;
    }

    public void setRouteEnd(String routeEnd) {
        this.routeEnd = routeEnd;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
