package Start.Model;

import javax.persistence.*;
import java.io.Serializable;




    @Entity
    @Table(name = "WeekSchedule")
    public class ScheduleRow implements Serializable {
        public ScheduleRow(String pon, String wt, String sr, String czw, String pt, String sob, String niedz) {
            this.pon = pon;
            this.wt = wt;
            this.sr = sr;
            this.czw = czw;
            this.pt = pt;
            this.sob = sob;
            this.niedz = niedz;
        }

        public ScheduleRow(){
            pon=wt=sr=czw=pt=sob=niedz="BRAK";
        }
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private int id;
        @Column(name ="pon")
        private String pon;

        @Column(name ="wt")
        private String wt;
        @Column(name ="sr")
        private String sr;

        @Column(name ="czw")
        private String czw;

        @Column(name ="pt")
        private String pt;
        @Column(name ="sob")
        private String sob;
        @Column(name ="niedz")
        private String niedz;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPon() {
            return pon;
        }

        public void setPon(String pon) {
            this.pon = pon;
        }

        public String getWt() {
            return wt;
        }

        public void setWt(String wt) {
            this.wt = wt;
        }

        public String getSr() {
            return sr;
        }

        public void setSr(String sr) {
            this.sr = sr;
        }

        public String getCzw() {
            return czw;
        }

        public void setCzw(String czw) {
            this.czw = czw;
        }

        public String getPt() {
            return pt;
        }

        public void setPt(String pt) {
            this.pt = pt;
        }

        public String getSob() {
            return sob;
        }

        public void setSob(String sob) {
            this.sob = sob;
        }

        public String getNiedz() {
            return niedz;
        }

        public void setNiedz(String niedz) {
            this.niedz = niedz;
        }
    }
