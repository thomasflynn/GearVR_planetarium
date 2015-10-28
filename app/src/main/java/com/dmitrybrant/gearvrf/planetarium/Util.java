/* Copyright 2015 Dmitry Brant
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dmitrybrant.gearvrf.planetarium;

import com.mhuss.Util.Str;

public class Util {
    public static final String TAG = "Util";

    public static String formatAsHtml(String content) {
        String html = "<html><head><style type=\"text/css\">" +
                "body{background-color:#303030;color:#c0c0c0;}" +
                "a{color:#fff;text-decoration:none;}" +
                "</style></head><body>";
        html += content;
        html += "</body>";
        return html;
    }

    public static float hmsToDec(float h, float m, float s) {
        float ret;
        ret = h * 15f;
        ret += m * 0.25f;
        ret += s * 0.00416667f;
        return ret;
    }

    public static float dmsToDec(float d, float m, float s) {
        float ret = d;
        if (d < 0f) {
            ret -= m * 0.016666667f;
            ret -= s * 2.77777778e-4f;
        } else {
            ret += m * 0.016666667f;
            ret += s * 2.77777778e-4f;
        }
        return ret;
    }

    public static String transformObjectName(String origName) {
        String name = origName;
        name = " " + name + " ";

        // wikify certain names
        name = name.replace("Mercury", "Mercury (planet)");

        // convert Bayer designations
        name = name.replace(" ALF", "Alpha");
        name = name.replace(" BET", "Beta");
        name = name.replace(" GAM", "Gamma");
        name = name.replace(" DEL", "Delta");
        name = name.replace(" EPS", "Epsilon");
        name = name.replace(" ZET", "Zeta");
        name = name.replace(" ETA", "Eta");
        name = name.replace(" TET", "Theta");
        name = name.replace(" IOT", "Iota");
        name = name.replace(" KAP", "Kappa");
        name = name.replace(" LAM", "Lambda");
        name = name.replace(" MU", "Mu");
        name = name.replace(" NU", "Nu");
        name = name.replace(" XI", "Xi");
        name = name.replace(" OMI", "Omicron");
        name = name.replace(" PI", "Pi");
        name = name.replace(" RHO", "Rho");
        name = name.replace(" SIG", "Sigma");
        name = name.replace(" TAU", "Tau");
        name = name.replace(" UPS", "Upsilon");
        name = name.replace(" PHI", "Phi");
        name = name.replace(" CHI", "Chi");
        name = name.replace(" PSI", "Psi");
        name = name.replace(" OME", "Omega");

        // convert constellation genitives
        name = name.replace(" And ", " Andromedae");
        name = name.replace(" Ant ", " Antliae");
        name = name.replace(" Aps ", " Apodis");
        name = name.replace(" Aqr ", " Aquarii");
        name = name.replace(" Aql ", " Aquilae");
        name = name.replace(" Ara ", " Arae");
        name = name.replace(" Ari ", " Arietis");
        name = name.replace(" Aur ", " Aurigae");
        name = name.replace(" Boo ", " Boötis");
        name = name.replace(" Cae ", " Caeli");
        name = name.replace(" Cam ", " Camelopardalis");
        name = name.replace(" Cnc ", " Cancri");
        name = name.replace(" CVn ", " Canum");
        name = name.replace(" CMa ", " Canis");
        name = name.replace(" CMi ", " Canis");
        name = name.replace(" Cap ", " Capricorni");
        name = name.replace(" Car ", " Carinae");
        name = name.replace(" Cas ", " Cassiopeiae");
        name = name.replace(" Cen ", " Centauri");
        name = name.replace(" Cep ", " Cephei");
        name = name.replace(" Cet ", " Ceti");
        name = name.replace(" Cha ", " Chamaeleontis");
        name = name.replace(" Cir ", " Circini");
        name = name.replace(" Col ", " Columbae");
        name = name.replace(" Com ", " Comae");
        name = name.replace(" CrA ", " Coronae");
        name = name.replace(" CrB ", " Coronae");
        name = name.replace(" Crv ", " Corvi");
        name = name.replace(" Crt ", " Crateris");
        name = name.replace(" Cru ", " Crucis");
        name = name.replace(" Cyg ", " Cygni");
        name = name.replace(" Del ", " Delphini");
        name = name.replace(" Dor ", " Doradus");
        name = name.replace(" Dra ", " Draconis");
        name = name.replace(" Equ ", " Equulei");
        name = name.replace(" Eri ", " Eridani");
        name = name.replace(" For ", " Fornacis");
        name = name.replace(" Gem ", " Geminorum");
        name = name.replace(" Gru ", " Gruis");
        name = name.replace(" Her ", " Herculis");
        name = name.replace(" Hor ", " Horologii");
        name = name.replace(" Hya ", " Hydrae");
        name = name.replace(" Hyi ", " Hydri");
        name = name.replace(" Ind ", " Indi");
        name = name.replace(" Lac ", " Lacertae");
        name = name.replace(" Leo ", " Leonis");
        name = name.replace(" LMi ", " Leonis");
        name = name.replace(" Lep ", " Leporis");
        name = name.replace(" Lib ", " Librae");
        name = name.replace(" Lup ", " Lupi");
        name = name.replace(" Lyn ", " Lyncis");
        name = name.replace(" Lyr ", " Lyrae");
        name = name.replace(" Men ", " Mensae");
        name = name.replace(" Mic ", " Microscopii");
        name = name.replace(" Mon ", " Monocerotis");
        name = name.replace(" Mus ", " Muscae");
        name = name.replace(" Nor ", " Normae");
        name = name.replace(" Oct ", " Octantis");
        name = name.replace(" Oph ", " Ophiuchi");
        name = name.replace(" Ori ", " Orionis");
        name = name.replace(" Pav ", " Pavonis");
        name = name.replace(" Peg ", " Pegasi");
        name = name.replace(" Per ", " Persei");
        name = name.replace(" Phe ", " Phoenicis");
        name = name.replace(" Pic ", " Pictoris");
        name = name.replace(" Psc ", " Piscium");
        name = name.replace(" PsA ", " Piscis");
        name = name.replace(" Pup ", " Puppis");
        name = name.replace(" Pyx ", " Pyxidis");
        name = name.replace(" Ret ", " Reticuli");
        name = name.replace(" Sge ", " Sagittae");
        name = name.replace(" Sgr ", " Sagittarii");
        name = name.replace(" Sco ", " Scorpii");
        name = name.replace(" Scl ", " Sculptoris");
        name = name.replace(" Sct ", " Scuti");
        name = name.replace(" Ser ", " Serpentis");
        name = name.replace(" Sex ", " Sextantis");
        name = name.replace(" Tau ", " Tauri");
        name = name.replace(" Tel ", " Telescopii");
        name = name.replace(" Tri ", " Trianguli");
        name = name.replace(" TrA ", " Trianguli");
        name = name.replace(" Tuc ", " Tucanae");
        name = name.replace(" UMa ", " Ursae");
        name = name.replace(" UMi ", " Ursae");
        name = name.replace(" Vel ", " Velorum");
        name = name.replace(" Vir ", " Virginis");
        name = name.replace(" Vol ", " Volantis");
        name = name.replace(" Vul ", " Vulpeculae");

        return name.trim();
    }
}
