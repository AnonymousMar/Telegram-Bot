package javacor.feature.featureCurrency.dto;

public class CurrencyItem {
    private Currency ccy;
    private Currency base_ccy;
    private float buy;
    private float sale;

    public Currency getCcy() {
        return ccy;
    }

    public Currency getBase_ccy() {
        return base_ccy;
    }

    public float getBuy() {
        return buy;
    }

    public float getSale() {
        return sale;
    }

    public void setCcy(Currency ccy) {
        this.ccy = ccy;
    }

    public void setBase_ccy(Currency base_ccy) {
        this.base_ccy = base_ccy;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "CurrencyItem{" +
                "ccy=" + ccy +
                ", base_ccy=" + base_ccy +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }

}
