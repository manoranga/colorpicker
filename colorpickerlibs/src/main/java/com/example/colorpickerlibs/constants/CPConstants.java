package com.example.colorpickerlibs.constants;

import android.graphics.Color;


import com.example.colorpickerlibs.models.GradientColorPickerModel;

import java.util.ArrayList;

public class CPConstants {
    public static final int RC_SIGN_IN = 1234;
    public static final int CPItemCount = 30;

    private static ArrayList<Integer> emptyColorPalette = new ArrayList<>();
    private static ArrayList<GradientColorPickerModel> emptyGradientColorPalette = new ArrayList<>();
    private static ArrayList<Integer> cpDarkEarthy = new ArrayList<>();
    private static ArrayList<Integer> cpSkin = new ArrayList<>();
    private static ArrayList<Integer> cpWarm = new ArrayList<>();
    private static ArrayList<Integer> cpCoolBlue = new ArrayList<>();
    private static ArrayList<Integer> cpRefreshing = new ArrayList<>();
    private static ArrayList<Integer> cpWaterBlueGreens = new ArrayList<>();
    private static ArrayList<Integer> cpOutdoorNature = new ArrayList<>();
    private static ArrayList<Integer> cpShadesOfPurple = new ArrayList<>();
    private static ArrayList<Integer> cpShadesOfGrey = new ArrayList<>();
    private static ArrayList<Integer> cpBrightOrange = new ArrayList<>();
    private static ArrayList<Integer> cpUSA = new ArrayList<>();
    private static ArrayList<Integer> doodleDeskColors = new ArrayList<>();

    public static ArrayList<Integer> getEmptyColorPalette() {
        if (emptyColorPalette.size() > 0) {
            return emptyColorPalette;
        }

        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);
        emptyColorPalette.add(0);

        return emptyColorPalette;
    }

    public static ArrayList<Integer> getCpDarkEarthy() {
        if (cpDarkEarthy.size() > 0) {
            return cpDarkEarthy;
        }

        cpDarkEarthy.add(Color.parseColor("#050403"));
        cpDarkEarthy.add(Color.parseColor("#24100A"));
        cpDarkEarthy.add(Color.parseColor("#3C170B"));
        cpDarkEarthy.add(Color.parseColor("#260F0B"));
        cpDarkEarthy.add(Color.parseColor("#0E060A"));
        cpDarkEarthy.add(Color.parseColor("#32140E"));
        cpDarkEarthy.add(Color.parseColor("#5F1E10"));
        cpDarkEarthy.add(Color.parseColor("#3B150D"));
        cpDarkEarthy.add(Color.parseColor("#1D0D0D"));
        cpDarkEarthy.add(Color.parseColor("#551D0D"));
        cpDarkEarthy.add(Color.parseColor("#87391C"));
        cpDarkEarthy.add(Color.parseColor("#7A2D17"));
        cpDarkEarthy.add(Color.parseColor("#280F0C"));
        cpDarkEarthy.add(Color.parseColor("#632A19"));
        cpDarkEarthy.add(Color.parseColor("#A2492E"));
        cpDarkEarthy.add(Color.parseColor("#7A2D17"));
        cpDarkEarthy.add(Color.parseColor("#43170D"));
        cpDarkEarthy.add(Color.parseColor("#854232"));
        cpDarkEarthy.add(Color.parseColor("#C47357"));
        cpDarkEarthy.add(Color.parseColor("#A65038"));
        cpDarkEarthy.add(Color.parseColor("#621F0D"));
        cpDarkEarthy.add(Color.parseColor("#A36051"));
        cpDarkEarthy.add(Color.parseColor("#DD9A83"));
        cpDarkEarthy.add(Color.parseColor("#C47561"));
        cpDarkEarthy.add(Color.parseColor("#A95339"));
        cpDarkEarthy.add(Color.parseColor("#C99587"));
        cpDarkEarthy.add(Color.parseColor("#F1CFC3"));
        cpDarkEarthy.add(Color.parseColor("#E9B2A1"));
        cpDarkEarthy.add(Color.parseColor("#D09F8F"));
        cpDarkEarthy.add(Color.parseColor("#F3D8CB"));

        return cpDarkEarthy;
    }

    public static ArrayList<Integer> getCpSkin() {
        if (cpSkin.size() > 0) {
            return cpSkin;
        }

        cpSkin.add(Color.parseColor("#86461E"));
        cpSkin.add(Color.parseColor("#AB783A"));
        cpSkin.add(Color.parseColor("#E4BD80"));
        cpSkin.add(Color.parseColor("#AA6027"));
        cpSkin.add(Color.parseColor("#D09B5C"));
        cpSkin.add(Color.parseColor("#EFDB9E"));
        cpSkin.add(Color.parseColor("#DA8137"));
        cpSkin.add(Color.parseColor("#EAB77B"));
        cpSkin.add(Color.parseColor("#FAEAC3"));
        cpSkin.add(Color.parseColor("#F9A052"));
        cpSkin.add(Color.parseColor("#F8CC95"));
        cpSkin.add(Color.parseColor("#FFF4D1"));
        cpSkin.add(Color.parseColor("#FAAD69"));
        cpSkin.add(Color.parseColor("#FDCFAA"));
        cpSkin.add(Color.parseColor("#F6F7D7"));
        cpSkin.add(Color.parseColor("#FBC083"));
        cpSkin.add(Color.parseColor("#FDDCB3"));
        cpSkin.add(Color.parseColor("#F6F7D7"));
        cpSkin.add(Color.parseColor("#F9BF76"));
        cpSkin.add(Color.parseColor("#FDDDB1"));
        cpSkin.add(Color.parseColor("#FEFDED"));
        cpSkin.add(Color.parseColor("#FECE9B"));
        cpSkin.add(Color.parseColor("#FDE6C6"));
        cpSkin.add(Color.parseColor("#FEFBF3"));
        cpSkin.add(Color.parseColor("#FAE1C1"));
        cpSkin.add(Color.parseColor("#FCF1E0"));
        cpSkin.add(Color.parseColor("#E9EBE1"));
        cpSkin.add(Color.parseColor("#FDE4C9"));
        cpSkin.add(Color.parseColor("#FBF1EA"));
        cpSkin.add(Color.parseColor("#FFFFFF"));

        return cpSkin;
    }

    public static ArrayList<Integer> getCpWarm() {
        if (cpWarm.size() > 0) {
            return cpWarm;
        }

        cpWarm.add(Color.parseColor("#7C1228"));
        cpWarm.add(Color.parseColor("#80132F"));
        cpWarm.add(Color.parseColor("#601128"));
        cpWarm.add(Color.parseColor("#610A1E"));
        cpWarm.add(Color.parseColor("#3F1319"));
        cpWarm.add(Color.parseColor("#691018"));
        cpWarm.add(Color.parseColor("#4C250F"));
        cpWarm.add(Color.parseColor("#7B3018"));
        cpWarm.add(Color.parseColor("#621F0D"));
        cpWarm.add(Color.parseColor("#7F2818"));
        cpWarm.add(Color.parseColor("#68231C"));
        cpWarm.add(Color.parseColor("#6B1318"));
        cpWarm.add(Color.parseColor("#A44123"));
        cpWarm.add(Color.parseColor("#A24023"));
        cpWarm.add(Color.parseColor("#C54127"));
        cpWarm.add(Color.parseColor("#AF3324"));
        cpWarm.add(Color.parseColor("#992A20"));
        cpWarm.add(Color.parseColor("#D02528"));
        cpWarm.add(Color.parseColor("#9D1C32"));
        cpWarm.add(Color.parseColor("#A71D3D"));
        cpWarm.add(Color.parseColor("#8F1834"));
        cpWarm.add(Color.parseColor("#A71E29"));
        cpWarm.add(Color.parseColor("#9A1B2F"));
        cpWarm.add(Color.parseColor("#B8202E"));
        cpWarm.add(Color.parseColor("#CE1F3E"));
        cpWarm.add(Color.parseColor("#DD1B52"));
        cpWarm.add(Color.parseColor("#D11F45"));
        cpWarm.add(Color.parseColor("#DC1F2F"));
        cpWarm.add(Color.parseColor("#D91F2B"));
        cpWarm.add(Color.parseColor("#E82527"));

        return cpWarm;
    }

    public static ArrayList<Integer> getCpCoolBlue() {
        if (cpCoolBlue.size() > 0) {
            return cpCoolBlue;
        }

        cpCoolBlue.add(Color.parseColor("#253D8F"));
        cpCoolBlue.add(Color.parseColor("#2B419A"));
        cpCoolBlue.add(Color.parseColor("#3D71B8"));
        cpCoolBlue.add(Color.parseColor("#7CA9DA"));
        cpCoolBlue.add(Color.parseColor("#C8D9E8"));
        cpCoolBlue.add(Color.parseColor("#AECDE7"));
        cpCoolBlue.add(Color.parseColor("#7CB2E0"));
        cpCoolBlue.add(Color.parseColor("#334FA2"));
        cpCoolBlue.add(Color.parseColor("#263C81"));
        cpCoolBlue.add(Color.parseColor("#1957A7"));
        cpCoolBlue.add(Color.parseColor("#345FAC"));
        cpCoolBlue.add(Color.parseColor("#2B92D0"));
        cpCoolBlue.add(Color.parseColor("#9EC8E3"));
        cpCoolBlue.add(Color.parseColor("#88C5E4"));
        cpCoolBlue.add(Color.parseColor("#5DB4E2"));
        cpCoolBlue.add(Color.parseColor("#2A9FDA"));
        cpCoolBlue.add(Color.parseColor("#326CB5"));
        cpCoolBlue.add(Color.parseColor("#1158A0"));
        cpCoolBlue.add(Color.parseColor("#ADDBE4"));
        cpCoolBlue.add(Color.parseColor("#B1D9E6"));
        cpCoolBlue.add(Color.parseColor("#5DBFE6"));
        cpCoolBlue.add(Color.parseColor("#25A5DE"));
        cpCoolBlue.add(Color.parseColor("#3279BE"));
        cpCoolBlue.add(Color.parseColor("#1068A2"));
        cpCoolBlue.add(Color.parseColor("#6BCCE8"));
        cpCoolBlue.add(Color.parseColor("#1BB8E2"));
        cpCoolBlue.add(Color.parseColor("#2489CA"));
        cpCoolBlue.add(Color.parseColor("#1476AF"));
        cpCoolBlue.add(Color.parseColor("#1485A1"));
        cpCoolBlue.add(Color.parseColor("#1599BB"));

        return cpCoolBlue;
    }

    public static ArrayList<Integer> getCpRefreshing() {
        if (cpRefreshing.size() > 0) {
            return cpRefreshing;
        }

        cpRefreshing.add(Color.parseColor("#F5ECB2"));
        cpRefreshing.add(Color.parseColor("#F7E694"));
        cpRefreshing.add(Color.parseColor("#FDD448"));
        cpRefreshing.add(Color.parseColor("#F9C312"));
        cpRefreshing.add(Color.parseColor("#C9A32D"));
        cpRefreshing.add(Color.parseColor("#AF9130"));
        cpRefreshing.add(Color.parseColor("#F2DB3B"));
        cpRefreshing.add(Color.parseColor("#EABC1F"));
        cpRefreshing.add(Color.parseColor("#CCA72C"));
        cpRefreshing.add(Color.parseColor("#AA8F31"));
        cpRefreshing.add(Color.parseColor("#806F2C"));
        cpRefreshing.add(Color.parseColor("#8A792F"));
        cpRefreshing.add(Color.parseColor("#F4E165"));
        cpRefreshing.add(Color.parseColor("#F3E691"));
        cpRefreshing.add(Color.parseColor("#FCE088"));
        cpRefreshing.add(Color.parseColor("#FBD36A"));
        cpRefreshing.add(Color.parseColor("#FAC94D"));
        cpRefreshing.add(Color.parseColor("#FAB417"));
        cpRefreshing.add(Color.parseColor("#D18E29"));
        cpRefreshing.add(Color.parseColor("#A8762B"));
        cpRefreshing.add(Color.parseColor("#7E5A24"));
        cpRefreshing.add(Color.parseColor("#89702B"));
        cpRefreshing.add(Color.parseColor("#B48C2E"));
        cpRefreshing.add(Color.parseColor("#DBA027"));
        cpRefreshing.add(Color.parseColor("#F7AD1A"));
        cpRefreshing.add(Color.parseColor("#FCC96A"));
        cpRefreshing.add(Color.parseColor("#FBD793"));
        cpRefreshing.add(Color.parseColor("#FDDC9D"));
        cpRefreshing.add(Color.parseColor("#F3D874"));
        cpRefreshing.add(Color.parseColor("#F3CD4C"));

        return cpRefreshing;
    }

    public static ArrayList<Integer> getCpWaterBlueGreens() {
        if (cpWaterBlueGreens.size() > 0) {
            return cpWaterBlueGreens;
        }

        cpWaterBlueGreens.add(Color.parseColor("#B8E1DD"));
        cpWaterBlueGreens.add(Color.parseColor("#6DC9C7"));
        cpWaterBlueGreens.add(Color.parseColor("#1EBCB9"));
        cpWaterBlueGreens.add(Color.parseColor("#0B9B94"));
        cpWaterBlueGreens.add(Color.parseColor("#008983"));
        cpWaterBlueGreens.add(Color.parseColor("#0B7F79"));
        cpWaterBlueGreens.add(Color.parseColor("#76CCCA"));
        cpWaterBlueGreens.add(Color.parseColor("#3ABFBC"));
        cpWaterBlueGreens.add(Color.parseColor("#0B9B94"));
        cpWaterBlueGreens.add(Color.parseColor("#08726A"));
        cpWaterBlueGreens.add(Color.parseColor("#035B50"));
        cpWaterBlueGreens.add(Color.parseColor("#036B66"));
        cpWaterBlueGreens.add(Color.parseColor("#9DD8D5"));
        cpWaterBlueGreens.add(Color.parseColor("#A3D9D4"));
        cpWaterBlueGreens.add(Color.parseColor("#6BC8C1"));
        cpWaterBlueGreens.add(Color.parseColor("#40BFB9"));
        cpWaterBlueGreens.add(Color.parseColor("#1FBBAE"));
        cpWaterBlueGreens.add(Color.parseColor("#05A699"));
        cpWaterBlueGreens.add(Color.parseColor("#03A996"));
        cpWaterBlueGreens.add(Color.parseColor("#0A9383"));
        cpWaterBlueGreens.add(Color.parseColor("#047E6F"));
        cpWaterBlueGreens.add(Color.parseColor("#035648"));
        cpWaterBlueGreens.add(Color.parseColor("#026154"));
        cpWaterBlueGreens.add(Color.parseColor("#0A9285"));
        cpWaterBlueGreens.add(Color.parseColor("#16BAA9"));
        cpWaterBlueGreens.add(Color.parseColor("#51C2B6"));
        cpWaterBlueGreens.add(Color.parseColor("#8ED1C6"));
        cpWaterBlueGreens.add(Color.parseColor("#C6E6D9"));
        cpWaterBlueGreens.add(Color.parseColor("#B2DED1"));
        cpWaterBlueGreens.add(Color.parseColor("#7FCDC1"));

        return cpWaterBlueGreens;
    }

    public static ArrayList<Integer> getCpOutdoorNature() {
        if (cpOutdoorNature.size() > 0) {
            return cpOutdoorNature;
        }

        cpOutdoorNature.add(Color.parseColor("#949436"));
        cpOutdoorNature.add(Color.parseColor("#AAAD37"));
        cpOutdoorNature.add(Color.parseColor("#C3CD2F"));
        cpOutdoorNature.add(Color.parseColor("#D1DD27"));
        cpOutdoorNature.add(Color.parseColor("#D7DF23"));
        cpOutdoorNature.add(Color.parseColor("#DEE34A"));
        cpOutdoorNature.add(Color.parseColor("#AEBC37"));
        cpOutdoorNature.add(Color.parseColor("#BFD730"));
        cpOutdoorNature.add(Color.parseColor("#D1DD27"));
        cpOutdoorNature.add(Color.parseColor("#D9E147"));
        cpOutdoorNature.add(Color.parseColor("#E1E777"));
        cpOutdoorNature.add(Color.parseColor("#E7E86B"));
        cpOutdoorNature.add(Color.parseColor("#A1A938"));
        cpOutdoorNature.add(Color.parseColor("#828334"));
        cpOutdoorNature.add(Color.parseColor("#667633"));
        cpOutdoorNature.add(Color.parseColor("#7FA23E"));
        cpOutdoorNature.add(Color.parseColor("#8BC63F"));
        cpOutdoorNature.add(Color.parseColor("#98CA3C"));
        cpOutdoorNature.add(Color.parseColor("#B7D87B"));
        cpOutdoorNature.add(Color.parseColor("#CDE29B"));
        cpOutdoorNature.add(Color.parseColor("#DBE9AF"));
        cpOutdoorNature.add(Color.parseColor("#DBE8A5"));
        cpOutdoorNature.add(Color.parseColor("#D4E48F"));
        cpOutdoorNature.add(Color.parseColor("#C4DB6C"));
        cpOutdoorNature.add(Color.parseColor("#6DBE45"));
        cpOutdoorNature.add(Color.parseColor("#63B646"));
        cpOutdoorNature.add(Color.parseColor("#649E42"));
        cpOutdoorNature.add(Color.parseColor("#687E36"));
        cpOutdoorNature.add(Color.parseColor("#48883F"));
        cpOutdoorNature.add(Color.parseColor("#479D46"));

        return cpOutdoorNature;
    }

    public static ArrayList<Integer> getCpShadesOfPurple() {
        if (cpShadesOfPurple.size() > 0) {
            return cpShadesOfPurple;
        }

        cpShadesOfPurple.add(Color.parseColor("#502654"));
        cpShadesOfPurple.add(Color.parseColor("#662A7B"));
        cpShadesOfPurple.add(Color.parseColor("#772B90"));
        cpShadesOfPurple.add(Color.parseColor("#A972B1"));
        cpShadesOfPurple.add(Color.parseColor("#CCA2CB"));
        cpShadesOfPurple.add(Color.parseColor("#CAADAE"));
        cpShadesOfPurple.add(Color.parseColor("#61315E"));
        cpShadesOfPurple.add(Color.parseColor("#502D45"));
        cpShadesOfPurple.add(Color.parseColor("#462834"));
        cpShadesOfPurple.add(Color.parseColor("#583344"));
        cpShadesOfPurple.add(Color.parseColor("#8C6876"));
        cpShadesOfPurple.add(Color.parseColor("#B2939B"));
        cpShadesOfPurple.add(Color.parseColor("#6E3572"));
        cpShadesOfPurple.add(Color.parseColor("#B28BB2"));
        cpShadesOfPurple.add(Color.parseColor("#C7A3C2"));
        cpShadesOfPurple.add(Color.parseColor("#CAB0B6"));
        cpShadesOfPurple.add(Color.parseColor("#AC879A"));
        cpShadesOfPurple.add(Color.parseColor("#916B81"));
        cpShadesOfPurple.add(Color.parseColor("#D383B7"));
        cpShadesOfPurple.add(Color.parseColor("#9B2487"));
        cpShadesOfPurple.add(Color.parseColor("#80246C"));
        cpShadesOfPurple.add(Color.parseColor("#5E2245"));
        cpShadesOfPurple.add(Color.parseColor("#4E223C"));
        cpShadesOfPurple.add(Color.parseColor("#744862"));
        cpShadesOfPurple.add(Color.parseColor("#E4A5CA"));
        cpShadesOfPurple.add(Color.parseColor("#E5A6AF"));
        cpShadesOfPurple.add(Color.parseColor("#D38A9B"));
        cpShadesOfPurple.add(Color.parseColor("#772939"));
        cpShadesOfPurple.add(Color.parseColor("#631F2C"));
        cpShadesOfPurple.add(Color.parseColor("#4D1F24"));

        return cpShadesOfPurple;
    }

    public static ArrayList<Integer> getCpShadesOfGrey() {
        if (cpShadesOfGrey.size() > 0) {
            return cpShadesOfGrey;
        }

        cpShadesOfGrey.add(Color.parseColor("#CACBCD"));
        cpShadesOfGrey.add(Color.parseColor("#BABABE"));
        cpShadesOfGrey.add(Color.parseColor("#A4A5AA"));
        cpShadesOfGrey.add(Color.parseColor("#A0A1A5"));
        cpShadesOfGrey.add(Color.parseColor("#9B9CA2"));
        cpShadesOfGrey.add(Color.parseColor("#98999E"));
        cpShadesOfGrey.add(Color.parseColor("#909195"));
        cpShadesOfGrey.add(Color.parseColor("#8D8E92"));
        cpShadesOfGrey.add(Color.parseColor("#898A8E"));
        cpShadesOfGrey.add(Color.parseColor("#86878B"));
        cpShadesOfGrey.add(Color.parseColor("#818286"));
        cpShadesOfGrey.add(Color.parseColor("#959599"));
        cpShadesOfGrey.add(Color.parseColor("#7e7f83"));
        cpShadesOfGrey.add(Color.parseColor("#7a7b7f"));
        cpShadesOfGrey.add(Color.parseColor("#75767a"));
        cpShadesOfGrey.add(Color.parseColor("#727378"));
        cpShadesOfGrey.add(Color.parseColor("#6e6e73"));
        cpShadesOfGrey.add(Color.parseColor("#6b6b6d"));
        cpShadesOfGrey.add(Color.parseColor("#474649"));
        cpShadesOfGrey.add(Color.parseColor("#4c4d4e"));
        cpShadesOfGrey.add(Color.parseColor("#4f4f51"));
        cpShadesOfGrey.add(Color.parseColor("#555657"));
        cpShadesOfGrey.add(Color.parseColor("#59585a"));
        cpShadesOfGrey.add(Color.parseColor("#5d5e60"));
        cpShadesOfGrey.add(Color.parseColor("#38383a"));
        cpShadesOfGrey.add(Color.parseColor("#2e2e30"));
        cpShadesOfGrey.add(Color.parseColor("#222224"));
        cpShadesOfGrey.add(Color.parseColor("#171719"));
        cpShadesOfGrey.add(Color.parseColor("#0c0c0c"));
        cpShadesOfGrey.add(Color.parseColor("#000000"));

        return cpShadesOfGrey;
    }

    public static ArrayList<Integer> getCpBrightOrange() {
        if (cpBrightOrange.size() > 0) {
            return cpBrightOrange;
        }

        cpBrightOrange.add(Color.parseColor("#c74e4a"));
        cpBrightOrange.add(Color.parseColor("#c76864"));
        cpBrightOrange.add(Color.parseColor("#b66158"));
        cpBrightOrange.add(Color.parseColor("#f15846"));
        cpBrightOrange.add(Color.parseColor("#f1624f"));
        cpBrightOrange.add(Color.parseColor("#f4806a"));
        cpBrightOrange.add(Color.parseColor("#A26C49"));
        cpBrightOrange.add(Color.parseColor("#B06A49"));
        cpBrightOrange.add(Color.parseColor("#E18457"));
        cpBrightOrange.add(Color.parseColor("#F8A286"));
        cpBrightOrange.add(Color.parseColor("#F37145"));
        cpBrightOrange.add(Color.parseColor("#FABEB2"));
        cpBrightOrange.add(Color.parseColor("#F4782B"));
        cpBrightOrange.add(Color.parseColor("#F5803E"));
        cpBrightOrange.add(Color.parseColor("#D69572"));
        cpBrightOrange.add(Color.parseColor("#F58537"));
        cpBrightOrange.add(Color.parseColor("#F9A772"));
        cpBrightOrange.add(Color.parseColor("#9C826E"));
        cpBrightOrange.add(Color.parseColor("#FCD1A6"));
        cpBrightOrange.add(Color.parseColor("#F3AA6C"));
        cpBrightOrange.add(Color.parseColor("#DBAD85"));
        cpBrightOrange.add(Color.parseColor("#D28A53"));
        cpBrightOrange.add(Color.parseColor("#EECFB8"));
        cpBrightOrange.add(Color.parseColor("#CB9671"));
        cpBrightOrange.add(Color.parseColor("#FBC083"));
        cpBrightOrange.add(Color.parseColor("#F7DDAC"));
        cpBrightOrange.add(Color.parseColor("#FAA833"));
        cpBrightOrange.add(Color.parseColor("#ECDBC2"));
        cpBrightOrange.add(Color.parseColor("#F9B948"));
        cpBrightOrange.add(Color.parseColor("#E5C691"));

        return cpBrightOrange;
    }

    public static ArrayList<Integer> getCpUSA() {
        if (cpUSA.size() > 0) {
            return cpUSA;
        }

        cpUSA.add(Color.parseColor("#09568d"));
        cpUSA.add(Color.parseColor("#172035"));
        cpUSA.add(Color.parseColor("#457725"));
        cpUSA.add(Color.parseColor("#686b69"));
        cpUSA.add(Color.parseColor("#ffbb00"));
        cpUSA.add(Color.parseColor("#f8a300"));
        cpUSA.add(Color.parseColor("#f0c282"));
        cpUSA.add(Color.parseColor("#ce3500"));
        cpUSA.add(Color.parseColor("#901E1D"));
        cpUSA.add(Color.parseColor("#421814"));
        cpUSA.add(Color.parseColor("#f0e9ee"));
        cpUSA.add(Color.parseColor("#a2a7ad"));
        cpUSA.add(Color.parseColor("#47484a"));
        cpUSA.add(Color.parseColor("#1c1d22"));
        cpUSA.add(Color.parseColor("#0b0b0d"));
        cpUSA.add(Color.parseColor("#122b3e"));
        cpUSA.add(Color.parseColor("#f1eaee"));
        cpUSA.add(Color.parseColor("#b0aaad"));
        cpUSA.add(Color.parseColor("#5b5e6a"));
        cpUSA.add(Color.parseColor("#343236"));
        cpUSA.add(Color.parseColor("#363848"));
        cpUSA.add(Color.parseColor("#262f39"));
        cpUSA.add(Color.parseColor("#373a42"));
        cpUSA.add(Color.parseColor("#646e83"));
        cpUSA.add(Color.parseColor("#7284b7"));
        cpUSA.add(Color.parseColor("#acac9a"));
        cpUSA.add(Color.parseColor("#65623c"));
        cpUSA.add(Color.parseColor("#46554f"));
        cpUSA.add(Color.parseColor("#4d4c30"));
        cpUSA.add(Color.parseColor("#ffaa07"));

        return cpUSA;
    }

    public static ArrayList<Integer> getDoodleDeskColors() {
        if (doodleDeskColors.size() > 0) {
            return doodleDeskColors;
        }

        doodleDeskColors.add(Color.parseColor("#002646"));
        doodleDeskColors.add(Color.parseColor("#124559"));
        doodleDeskColors.add(Color.parseColor("#339989"));
        doodleDeskColors.add(Color.parseColor("#7de2d1"));
        doodleDeskColors.add(Color.parseColor("#b1f8ec"));
        doodleDeskColors.add(Color.parseColor("#fdca40"));
        doodleDeskColors.add(Color.parseColor("#f79824"));
        doodleDeskColors.add(Color.parseColor("#d86f05"));
        doodleDeskColors.add(Color.parseColor("#b44000"));
        doodleDeskColors.add(Color.parseColor("#b43514"));
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);
        doodleDeskColors.add(0);

        return doodleDeskColors;
    }

    public static ArrayList<GradientColorPickerModel> getEmptyGradientPalette() {
        if (emptyGradientColorPalette.size() > 0) {
            return emptyGradientColorPalette;
        }

        emptyGradientColorPalette.add(new GradientColorPickerModel(new int[]{Color.parseColor("#DE9DD4"), Color.parseColor("#FFFFFD"), Color.parseColor("#FEE5A0"), Color.parseColor("#FD955B")}, new float[]{0f, 0.3f, 0.7f, 1f}, true));
        emptyGradientColorPalette.add(new GradientColorPickerModel(new int[]{Color.parseColor("#000617"), Color.parseColor("#0E3C53"), Color.parseColor("#E17B00"), Color.parseColor("#B53500")}, new float[]{0f, 0.3f, 0.7f, 1f}, true));
        emptyGradientColorPalette.add(new GradientColorPickerModel(new int[]{Color.parseColor("#373B44"), Color.parseColor("#4286F4")}, new float[]{0f, 1f}, true));
        emptyGradientColorPalette.add(new GradientColorPickerModel(new int[]{Color.parseColor("#095402"), Color.parseColor("#168902"), Color.parseColor("#084F00"), Color.parseColor("#0A1009")}, new float[]{0f, 0.3f, 0.7f, 1f}, true));
        emptyGradientColorPalette.add(new GradientColorPickerModel(new int[]{Color.parseColor("#31883A"), Color.parseColor("#BC2A28")}, new float[]{0f, 1f}, true));
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);
        emptyGradientColorPalette.add(null);

        return emptyGradientColorPalette;
    }
}
