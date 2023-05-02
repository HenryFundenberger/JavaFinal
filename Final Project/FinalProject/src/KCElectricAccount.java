public class KCElectricAccount extends Customer {


    private String name;
    private int KCElectricID;
    private double EnergyTariff;
    private String meterType;
    private double amountPerKWH;
    private double lastMonthMeterReading;
    private double thisMonthMeterReading;

    private String dueDate;


    public KCElectricAccount(String name, int KCElectricID, double EnergyTariff, String meterType, double amountPerKWH, double lastMonthMeterReading, double thisMonthMeterReading, String dueDate) {
        super(name, KCElectricID);
        this.name = name;
        this.KCElectricID = KCElectricID;
        this.EnergyTariff = EnergyTariff;
        this.meterType = meterType;
        this.amountPerKWH = amountPerKWH;
        this.lastMonthMeterReading = lastMonthMeterReading;
        this.thisMonthMeterReading = thisMonthMeterReading;
        this.dueDate = dueDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKCElectricID() {
        return KCElectricID;
    }

    public void setKCElectricID(int KCElectricID) {
        this.KCElectricID = KCElectricID;
    }

    public double getEnergyTariff() {
        return EnergyTariff;
    }

    public void setEnergyTariff(double energyTariff) {
        EnergyTariff = energyTariff;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public double getAmountPerKWH() {
        return amountPerKWH;
    }

    public void setAmountPerKWH(double amountPerKWH) {
        this.amountPerKWH = amountPerKWH;
    }

    public double getLastMonthMeterReading() {
        return lastMonthMeterReading;
    }

    public void setLastMonthMeterReading(double lastMonthMeterReading) {
        this.lastMonthMeterReading = lastMonthMeterReading;
    }

    public double getThisMonthMeterReading() {
        return thisMonthMeterReading;
    }

    public void setThisMonthMeterReading(double thisMonthMeterReading) {
        this.thisMonthMeterReading = thisMonthMeterReading;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
