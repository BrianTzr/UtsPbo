package uts;

import java.util.ArrayList;
import java.util.Scanner;

public class BankingSystem {
    private ArrayList<BankAccount> accounts;

    public BankingSystem() {
        this.accounts = new ArrayList<>();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            showMenu();
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registrasiAkunBaru(scanner);
                    break;
                case 2:
                    kirimUang(scanner);
                    break;
                case 3:
                    simpanUang(scanner);
                    break;
                case 4:
                    cekInformasiRekening(scanner);
                    break;
                case 5:
                    System.out.println("Terima kasih.");
                    break;
                default:
                    System.out.println("Menu tidak valid.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }

    public void showMenu() {
        System.out.println("+----------------------------------------+");
        System.out.println("|         === Menu Banking System ===    |");
        System.out.println("| 1. Registrasi Akun Baru               |");
        System.out.println("| 2. Kirim Uang                          |");
        System.out.println("| 3. Simpan Uang                         |");
        System.out.println("| 4. Cek Informasi Rekening              |");
        System.out.println("| 5. Keluar                              |");
        System.out.println("+----------------------------------------+");
    }

    public void registrasiAkunBaru(Scanner scanner) {
        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("Masukkan nomor telepon: ");
        String nomorTelepon = scanner.nextLine();
        System.out.print("Masukkan saldo awal: Rp ");
        int saldoAwal = scanner.nextInt();
        scanner.nextLine(); // consume newline
        BankAccount newAccount = new BankAccount(nama, alamat, nomorTelepon, saldoAwal);
        accounts.add(newAccount);

        // Display account details
        System.out.println("Akun baru berhasil diregistrasi. Berikut adalah detail akun:");
        newAccount.showDescription();
    }

    public void kirimUang(Scanner scanner) {
        System.out.print("Masukkan nomor akun pengirim: ");
        String nomorAkunPengirim = scanner.nextLine();
        System.out.print("Masukkan nomor akun penerima: ");
        String nomorAkunPenerima = scanner.nextLine();
        System.out.print("Masukkan jumlah uang yang akan ditransfer:Rp ");
        int jumlahUang = scanner.nextInt();
        scanner.nextLine(); // consume newline
        BankAccount pengirim = findAccountByNomorAkun(nomorAkunPengirim);
        BankAccount penerima = findAccountByNomorAkun(nomorAkunPenerima);
        if (pengirim != null && penerima != null) {
            if (pengirim.getSaldo() >= jumlahUang) {
                pengirim.transfer(jumlahUang);
                penerima.topUp(jumlahUang);
                System.out.println("Transfer berhasil.");
            } else {
                System.out.println("Saldo tidak mencukupi untuk transfer.");
            }
        } else {
            System.out.println("Nomor akun pengirim atau penerima tidak valid.");
        }
    }

    public void simpanUang(Scanner scanner) {
        System.out.print("Masukkan nomor akun: ");
        String nomorAkun = scanner.nextLine();
        System.out.print("Masukkan jumlah uang yang akan disimpan:Rp ");
        int jumlahUang = scanner.nextInt();
        scanner.nextLine(); // consume newline
        BankAccount account = findAccountByNomorAkun(nomorAkun);
        if (account != null) {
            account.topUp(jumlahUang);
            System.out.println("Uang berhasil disimpan.");
        } else {
            System.out.println("Nomor akun tidak valid.");
        }
    }

    public void cekInformasiRekening(Scanner scanner) {
        System.out.print("Masukkan nomor akun: ");
        String nomorAkun = scanner.nextLine();
        BankAccount account = findAccountByNomorAkun(nomorAkun);
        if (account != null) {
            account.showDescription();
        } else {
            System.out.println("Nomor akun tidak valid.");
        }
    }

    private BankAccount findAccountByNomorAkun(String nomorAkun) {
        for (BankAccount account : accounts) {
            if (account.getNomor_akun().equals(nomorAkun)) {
                return account;
            }
        }
        return null;
    }
}
