package w32;

import javafx.beans.property.*;

public class Covid {
    private final ReadOnlyIntegerWrapper id =
            new ReadOnlyIntegerWrapper(this, "id", 0); //idSequence.incrementAndGet());
    private final StringProperty id2 =
            new SimpleStringProperty(this, "id2", null);
    private final IntegerProperty datum =
            new SimpleIntegerProperty(this, "datum", 0);
    private final IntegerProperty vek =
            new SimpleIntegerProperty(this, "vek", 0);
    private final StringProperty pohlavi =
            new SimpleStringProperty(this, "pohlavi", null);
    private final StringProperty kraj =
            new SimpleStringProperty(this, "kraj", null);
    private final BooleanProperty okres =
            new SimpleBooleanProperty(this, "okres", false);
    private final StringProperty stat =
            new SimpleStringProperty(this, "stat", null);
    private final BooleanProperty reportovano =
            new SimpleBooleanProperty(this, "reportovano", false);

    public Covid(int id, String id2, int datum, int vek, String pohlavi, String kraj, Boolean okres, String stat,Boolean reportovano ) {
        this.id.set(id);
        this.id2.set(id2);
        this.datum.set(datum);
        this.vek.set(vek);
        this.pohlavi.set(pohlavi);
        this.kraj.set(kraj);
        this.okres.set(okres);
        this.stat.set(stat);
        this.reportovano.set(reportovano);

    }

    public int getId() {
        return id.get();
    }

    public ReadOnlyIntegerWrapper idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getId2() {
        return id2.get();
    }

    public StringProperty id2Property() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2.set(id2);
    }

    public int getDatum() {
        return datum.get();
    }

    public IntegerProperty datumProperty() {
        return datum;
    }

    public void setDatum(int datum) {
        this.datum.set(datum);
    }

    public int getVek() {
        return vek.get();
    }

    public IntegerProperty vekProperty() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek.set(vek);
    }

    public String getPohlavi() {
        return pohlavi.get();
    }

    public StringProperty pohlaviProperty() {
        return pohlavi;
    }

    public void setPohlavi(String pohlavi) {
        this.pohlavi.set(pohlavi);
    }

    public String getKraj() {
        return kraj.get();
    }

    public StringProperty krajProperty() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj.set(kraj);
    }

    public boolean isOkres() {
        return okres.get();
    }

    public BooleanProperty okresProperty() {
        return okres;
    }

    public void setOkres(boolean okres) {
        this.okres.set(okres);
    }

    public String getStat() {
        return stat.get();
    }

    public StringProperty statProperty() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat.set(stat);
    }

    public boolean isReportovano() {
        return reportovano.get();
    }

    public BooleanProperty reportovanoProperty() {
        return reportovano;
    }

    public void setReportovano(boolean reportovano) {
        this.reportovano.set(reportovano);
    }
}
