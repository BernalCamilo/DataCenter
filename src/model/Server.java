package model;

public class Server {
    //atriutes
    private double cache;
    private int numberOfProcessors;
    private double ram;
    private int numberOfDiscs;
    private double diskCapacity;

    //relations
    private ProcessorBrand processorBrand;

    //methods
    public Server(double cache, int numberOfProcessors, double ram, int numberOfDiscs, double diskCapacity, int number) {
        this.setCache(cache);
        this.setNumberOfProcessors(numberOfProcessors);
        this.setRam(ram);
        this.setNumberOfDiscs(numberOfDiscs);
        this.setDiskCapacity(diskCapacity);
        setProcessorBrannd(number);
    }
    
    /**
     * get cache
     * @return
     */
    public double getCache() {
        return cache;
    }

    /**
     * set cache
     * @param cache
     */
    public void setCache(double cache) {
        this.cache = cache;
    }

    /**
     * get Number Of Processors
     * @return
     */
    public int getNumberOfProcessors() {
        return numberOfProcessors;
    }

    /**
     * set Number Of Processors
     * @param numberOfProcessors
     */
    public void setNumberOfProcessors(int numberOfProcessors) {
        this.numberOfProcessors = numberOfProcessors;
    }

    /**
     * get ram
     * @return
     */
    public double getRam() {
        return ram;
    }

    /**
     * set ram
     * @param ram
     */
    public void setRam(double ram) {
        this.ram = ram;
    }

    /**
     * get Number Of Discs
     * @return
     */
    public int getNumberOfDiscs() {
        return numberOfDiscs;
    }

    /**
     * 
     * @param numberOfDiscs
     */
    public void setNumberOfDiscs(int numberOfDiscs) {
        this.numberOfDiscs = numberOfDiscs;
    }

    /**
     * get Disk Capacity
     * @return
     */
    public double getDiskCapacity() {
        return diskCapacity;
    }

    /**
     * set Disk Capacity
     * @param diskCapacity
     */
    public void setDiskCapacity(double diskCapacity) {
        this.diskCapacity = diskCapacity;
    }

    /**
     * set Processor Brannd
     * @param number
     */
    public void setProcessorBrannd (int number){
        switch (number){
            case 1:
            processorBrand=ProcessorBrand.AMD;
            break;
            case 2:
            processorBrand=ProcessorBrand.INTEL;
            break;
        }
    }

    /**
     * get Processor Brannd
     * @return
     */
    public ProcessorBrand getProcessorBrand(){
        return processorBrand;
    }
}
