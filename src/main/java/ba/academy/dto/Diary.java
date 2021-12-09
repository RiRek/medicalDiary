package ba.academy.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;
@Schema(name="Diary", description = "Diary representation")
public class Diary {

    private Long id;
    @Schema(required = true )
    private Date date;
    private String title;
    private MedicineType medicineType;
    private Medicines medicines;
    private int count;

    public Diary(Long id) {
        this.id = id;
    }

    public Diary(Long id, Date date, String title, MedicineType medicineType, Medicines medicines, int count) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.medicineType = medicineType;
        this.medicines = medicines;
        this.count = count;
    }

    public Diary(Date date, String title, MedicineType medicineType, Medicines medicines, int count) {
        this.date = date;
        this.title = title;
        this.medicineType = medicineType;
        this.medicines = medicines;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", medicineType=" + medicineType +
                ", medicines=" + medicines +
                ", count=" + count +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MedicineType getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(MedicineType medicineType) {
        this.medicineType = medicineType;
    }

    public Medicines getMedicines() {
        return medicines;
    }

    public void setMedicines(Medicines medicines) {
        this.medicines = medicines;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
