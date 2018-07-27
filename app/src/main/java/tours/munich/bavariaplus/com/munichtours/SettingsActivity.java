package tours.munich.bavariaplus.com.munichtours;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.preference.SwitchPreference;
import android.support.v4.os.ConfigurationCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {
    SwitchPreference betaAccess;
    ListPreference languagelist;
    MultiSelectListPreference starttabs;
    Locale systemlng;
    SharedPreferences settingspref;
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    Preference.OnPreferenceChangeListener betaaccesschanged = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            if (Boolean.valueOf(o.toString())) {
                AlertDialog.Builder warning = new AlertDialog.Builder(SettingsActivity.this);
                warning.setTitle(R.string.pref_warning_beta_acces_title);
                warning.setMessage(R.string.pref_warning_beta_acces_message);
                warning.setIcon(R.drawable.ic_warning_black_24dp);
                warning.setCancelable(false);
                warning.setPositiveButton("Trotzdem anwenden", null);
                warning.setNegativeButton("Nicht anwenden", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        betaAccess.setChecked(false);
                    }
                });
                warning.create().show();
                return true;
            } else {
                betaAccess.setChecked(false);
            }
            return false;
        }
    };
    Preference.OnPreferenceChangeListener languagelistchange = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            Log.v("onPfrefChLangList","preference :"+preference.toString()+" o:"+o.toString());
            String newVal = o.toString();
            Locale myLocale;
            switch(newVal){
                case "sys":
                    myLocale = new Locale(systemlng.getLanguage());
                    settingspref.edit().putString("lang","sys").apply();
                    break;
                default:
                    myLocale = new Locale(newVal);
                    settingspref.edit().putString("lang",myLocale.getLanguage()).apply();
            }
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(getApplicationContext(), MainActivity.class);
            finishAffinity();
            startActivity(refresh);
            return false;

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingspref = PreferenceManager.getDefaultSharedPreferences(this);
        systemlng = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
        addPreferencesFromResource(R.xml.pref_main);
        betaAccess = (SwitchPreference) findPreference("pref_enable_beta_access");
        languagelist = (ListPreference) findPreference("pref_language");
        starttabs = (MultiSelectListPreference) findPreference("pref_select_start_tabs");

        betaAccess.setOnPreferenceChangeListener(betaaccesschanged);

        Log.v("LanguageListPreference", "Selected Entry:" + languagelist.getEntry());
        String[] rawlangentries = getResources().getStringArray(R.array.language_select_entry);
        rawlangentries[0] = String.format(rawlangentries[0], systemlng.getDisplayLanguage());
        languagelist.setEntries(rawlangentries);
        languagelist.setOnPreferenceChangeListener(languagelistchange);
        initSettings();
    }

    public void initSettings() {
        //Setting up the languagelist Preference
        languagelist.setSummary(Locale.getDefault().getDisplayLanguage());
        if(!settingspref.getString("lang","").equals(null)){
            String lng = settingspref.getString("lang","");
            Log.v("SharedPreference","lng equals "+lng);
            switch(lng){
                case "sys":
                    languagelist.setValue("sys");
                    break;
                default:
                    languagelist.setValue(lng);
                    break;
            }
        }
    }
}
