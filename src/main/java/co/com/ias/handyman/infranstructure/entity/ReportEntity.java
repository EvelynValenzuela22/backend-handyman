package co.com.ias.handyman.infranstructure.entity;


public class ReportEntity {
    private final int id;

    private String idTechnician;
    private String idService;
    private String startDate;
    private String endDate;
    private int week;
    private String reportDescription;
    private int TotalHourService;

    public ReportEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getIdTechnician() {
        return idTechnician;
    }

    public String getIdService() {
        return idService;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getWeek() {
        return week;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public int getTotalHourService() {
        return TotalHourService;
    }

    public void setIdTechnician(String idTechnician) {
        this.idTechnician = idTechnician;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public void setTotalHourService(int totalHourService) {
        TotalHourService = totalHourService;
    }

    @Override
    public String toString() {
        return "ReportEntity{" +
                "id=" + id +
                ", idTechnician='" + idTechnician + '\'' +
                ", idService='" + idService + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", week=" + week +
                ", reportDescription='" + reportDescription + '\'' +
                ", TotalHourService=" + TotalHourService +
                '}';
    }

    public void setTotalHoursService(int calculateHourService) {
    }
}
