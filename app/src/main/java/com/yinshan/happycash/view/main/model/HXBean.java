package com.yinshan.happycash.view.main.model;

/**
 * Created by admin on 2018/4/16.
 */

public class HXBean {


    /**
     * id : 3861
     * tenantId : 53718
     * greetingId : e1972aa2-324d-45ec-8b78-930720bbbb48
     * greetingTextType : 1
     * greetingText : {&quot;ext&quot;:{&quot;msgtype&quot;:{&quot;choice&quot;:{&quot;list&quot;:[&quot;Masalah Pelunasan&quot;,&quot;Masalah verifikasi&quot;,&quot;Masalah pinjaman&quot;,&quot;Masalah pentransferan dana&quot;,&quot;Mengundang teman&quot;,&quot;Tentang produk rupiahplus&quot;,&quot;Akun dan keamanan&quot;,&quot;Masalah pengoperasian produk&quot;],&quot;items&quot;:[{&quot;id&quot;:&quot;00c2bab4-56c3-4558-ad1c-bcddee8ce13a&quot;,&quot;name&quot;:&quot;Masalah Pelunasan&quot;},{&quot;id&quot;:&quot;49553531-1bb5-4499-b724-2c5764252621&quot;,&quot;name&quot;:&quot;Masalah verifikasi&quot;},{&quot;id&quot;:&quot;247415e5-b491-4ead-87b5-2f2da19f805f&quot;,&quot;name&quot;:&quot;Masalah pinjaman&quot;},{&quot;id&quot;:&quot;0f562ac2-20e3-4c74-940b-c5a459b9d557&quot;,&quot;name&quot;:&quot;Masalah pentransferan dana&quot;},{&quot;id&quot;:&quot;8946cdbc-c116-4d2b-be98-cc8cdf094742&quot;,&quot;name&quot;:&quot;Mengundang teman&quot;},{&quot;id&quot;:&quot;74560f64-a5ea-45b0-99cf-273ac202db38&quot;,&quot;name&quot;:&quot;Tentang produk rupiahplus&quot;},{&quot;id&quot;:&quot;f924e49e-8df4-4021-ac60-ea63bb6a81e5&quot;,&quot;name&quot;:&quot;Akun dan keamanan&quot;},{&quot;id&quot;:&quot;243ba5d4-5053-412f-8dad-d123202a52b7&quot;,&quot;name&quot;:&quot;Masalah pengoperasian produk&quot;}],&quot;title&quot;:&quot;Halo, selamat datang di RupiahPlus! (Perhatian: Klik pada solusi berikut untuk masalah umum berdasarkan kategori untuk membantu menyelesaikan masalah Anda dengan cepat. Terima kasih!)&quot;}}}}
     * createdTime : 1523433605000
     * lastUpdatedTime : 1523850566000
     * greetingJson : null
     */

    private int id;
    private int tenantId;
    private String greetingId;
    private int greetingTextType;
    private String greetingText;
    private long createdTime;
    private long lastUpdatedTime;
    private Object greetingJson;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getGreetingId() {
        return greetingId;
    }

    public void setGreetingId(String greetingId) {
        this.greetingId = greetingId;
    }

    public int getGreetingTextType() {
        return greetingTextType;
    }

    public void setGreetingTextType(int greetingTextType) {
        this.greetingTextType = greetingTextType;
    }

    public String getGreetingText() {
        return greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Object getGreetingJson() {
        return greetingJson;
    }

    public void setGreetingJson(Object greetingJson) {
        this.greetingJson = greetingJson;
    }
}
