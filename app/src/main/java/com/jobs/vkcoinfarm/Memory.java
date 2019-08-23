package com.jobs.vkcoinfarm;

public final class Memory {
    public static int[] Values = {0, 0, 0, 0, 0, 0, 0};
    public static boolean vkpay = false, acc = false, ads_sup = false;
    public static boolean first_login = true, music = false;
    public static boolean first_ads = true;

    public static int[] Images = {R.drawable.grid_cursor,
            R.drawable.grid_videocard,
            R.drawable.grid_videocards,
            R.drawable.grid_super_pc,
            R.drawable.grid_server_vk,
            R.drawable.grid_quantum_pc,
            R.drawable.grid_datacenter};

    public static int[] Colors = {R.color.colorCursor,
            R.color.colorVideoCard,
            R.color.colorVideoCards,
            R.color.colorSuperPC,
            R.color.colorServerVK,
            R.color.colorQuantumPC,
            R.color.colorDataCenter};

    public static float[] Consts = {0.001f, 0.003f, 0.01f, 0.03f, 0.1f, 0.5f, 1f};

    public static float[] Cents = {0.03f, 0.1f, 1f, 10f, 50f, 200f, 5000f};

    public static String[] Names = {"Курсор", "Видеокарта", "Стойка\nвидеокарт", "Суперкомпьютер", "Сервер\nВКонтакте", "Квантовый\nкомпьютер", "Датацентр"};

    public static float Pice(int n)
    {
        return Cents[n] * (float)Math.pow(1.3, Values[n]);
    }
}
