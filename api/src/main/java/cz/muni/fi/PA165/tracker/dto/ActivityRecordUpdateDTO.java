package cz.muni.fi.PA165.tracker.dto;

public class ActivityRecordUpdateDTO extends ActivityRecordCreateDTO {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof ActivityRecordUpdateDTO)) {
            return false;
        }
        ActivityRecordUpdateDTO updateDTO = (ActivityRecordUpdateDTO) o;
        return super.equals(updateDTO) && (id != null && id.equals(updateDTO.getId()));
    }

    @Override
    public int hashCode() {
        return super.hashCode() + ((id == null) ? 0 : id.hashCode());
    }
}
