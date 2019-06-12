public class SystemConfig implements SystemConfigMBean  {
    public SystemConfig(int numberOfThreads, int size) {
        setThreadCount(numberOfThreads);
        setMapSize(size);
    }

    @Override
    public void setThreadCount(int numberOfThreads) {
        Main.numberOfThreads=numberOfThreads;
    }

    @Override
    public int getThreadCount() {
        return Main.numberOfThreads;
    }

    @Override
    public void setMapSize(int size) {
        ResultStorage.size=size;
    }

    @Override
    public int getMapSize() {
       return ResultStorage.size;
    }

    @Override
    public void setSeedRange(int range){
        Main.seedRange=range;
    }

    @Override
    public int getSeedRange() {
        return Main.seedRange;
    }

    @Override
    public String displayInfo() {
        String threads= "Number of threads is "+ getThreadCount() + "\n";
        String size = "Storage Size is: " + getMapSize() + "\n";
        String records = "There is " + ResultStorage.resultList.size() + "Records" + "\n";
        String ratio = "Hit&Kill ration is " +((double)ResultStorage.found/(double)ResultStorage.notfound)+" ratio" + "\n";
        return threads + size + records + ratio;
    }
}
