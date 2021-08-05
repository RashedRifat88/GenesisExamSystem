package com.genesis.genesisexamsystem.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.genesis.genesisexamsystem.R;
import com.genesis.genesisexamsystem.course.CourseActivity;
import com.genesis.genesisexamsystem.data.shared_pref.SharedData;

public class HomeFragment extends Fragment {

    LinearLayout linear_exam;
    TextView tv_more, tv_name, tv_bmdc_no;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        initView(root);
//        htmlParse(root);

        return root;
    }

//    private void htmlParse(View root) {
//        String str1 = "<p>a&nbsp;এর চর্তুঘাত থেকে a&nbsp;এর বর্গের বিয়োগফল -1&nbsp;হলে&nbsp;<img alt=\\\"fraction numerator a to the power of 4 over denominator a to the power of 8 minus a to the power of 4 plus 1 end fraction\\\" src=\\\"data:image/svg+xml;charset=utf8,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20xmlns%3Awrs%3D%22http%3A%2F%2Fwww.wiris.com%2Fxml%2Fmathml-extension%22%20height%3D%2249%22%20width%3D%2282%22%20wrs%3Abaseline%3D%2230%22%3E%3C!--MathML%3A%20%3Cmath%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F1998%2FMath%2FMathML%22%3E%3Cmfrac%3E%3Cmsup%3E%3Cmi%3Ea%3C%2Fmi%3E%3Cmn%3E4%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmrow%3E%3Cmsup%3E%3Cmi%3Ea%3C%2Fmi%3E%3Cmn%3E8%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E-%3C%2Fmo%3E%3Cmsup%3E%3Cmi%3Ea%3C%2Fmi%3E%3Cmn%3E4%3C%2Fmn%3E%3C%2Fmsup%3E%3Cmo%3E%2B%3C%2Fmo%3E%3Cmn%3E1%3C%2Fmn%3E%3C%2Fmrow%3E%3C%2Fmfrac%3E%3C%2Fmath%3E--%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%40font-face%7Bfont-family%3A'ae2ef524fbf3d9fe611d5a8e90fefdc'%3Bsrc%3Aurl(data%3Afont%2Ftruetype%3Bcharset%3Dutf-8%3Bbase64%2CAAEAAAAMAIAAAwBAT1MvMjv%2FLJYAAADMAAAATmNtYXDgWxEdAAABHAAAADRjdnQgAAAABwAAAVAAAAAEZ2x5ZoYrxVAAAAFUAAAA0WhlYWQOdyayAAACKAAAADZoaGVhC0UVwQAAAmAAAAAkaG10eCg8AIUAAAKEAAAACGxvY2EAAAVKAAACjAAAAAxtYXhwBIoEWwAAApgAAAAgbmFtZXSF9ZsAAAK4AAABrXBvc3QDogHPAAAEaAAAACBwcmVwukanGAAABIgAAAANAAAGtAGQAAUAAAgACAAAAAAACAAIAAAAAAAAAQIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAgICAAAAAg8AMGe%2F57AAAHPgGyAAAAAAACAAEAAQAAABQAAwABAAAAFAAEACAAAAAEAAQAAQAAAGH%2F%2FwAAAGH%2F%2F%2F%2BgAAEAAAAAAAAABwACAFUAAAMAA6sAAwAHAAAzESERJSERIVUCq%2F2rAgD%2BAAOr%2FFVVAwAAAwAt%2F3QEAwRZAAsAFwAdADsYAbAdELAD1LADELAU1LAUELAc1LAcELAJ1LAcELAOPLAJELAbPACwBhCwEdSwBhCwANSwABCwF9QwMQEiABEWEjMyEjcQJgYWAwIGIyImNTQ2MwE1BhMjEgIBs%2F7fFvWy07oDhYZwFgxOhVmysoUB7YwEslEEWf7f%2Ft71%2Ft8BM%2BMBp5yyLf6d%2FwBlyJzfsvxZjF0B5%2F1eAAAAAAEAAAABAACav9usXw889QADCAD%2F%2F%2F%2F%2F1a3uPf%2F%2F%2F%2F%2FVre49AAH%2B9QQDBkMAAAAKAAIAAQAAAAAAAQAABz7%2BTgAAF3AAAf%2F8BAMAAQAAAAAAAAAAAAAAAAAAAAIDUgBVBEwALQAAAAAAAAAoAAAA0QABAAAAAgAeAAMAAAAAAAIAgAQAAAAAAAQAADsAAAAAAAAAFQECAAAAAAAAAAEAFgAAAAAAAAAAAAIADgAWAAAAAAAAAAMANAAkAAAAAAAAAAQAFgBYAAAAAAAAAAUAFgBuAAAAAAAAAAYACwCEAAAAAAAAAAgAHACPAAEAAAAAAAEAFgAAAAEAAAAAAAIADgAWAAEAAAAAAAMANAAkAAEAAAAAAAQAFgBYAAEAAAAAAAUAFgBuAAEAAAAAAAYACwCEAAEAAAAAAAgAHACPAAMAAQQJAAEAFgAAAAMAAQQJAAIADgAWAAMAAQQJAAMANAAkAAMAAQQJAAQAFgBYAAMAAQQJAAUAFgBuAAMAAQQJAAYACwCEAAMAAQQJAAgAHACPAE0AYQB0AGgAIABGAG8AbgB0ACAAMgBSAGUAZwB1AGwAYQByAE0AYQB0AGgAcwAgAEYAbwByACAATQBvAHIAZQAgAE0AYQB0AGgAIABGAG8AbgB0ACAAMgBNAGEAdABoACAARgBvAG4AdAAgADIAVgBlAHIAcwBpAG8AbgAgADEALgAwTWF0aF9Gb250XzIATQBhAHQAaABzACAARgBvAHIAIABNAG8AcgBlAAAAAAMAAAAAAAADnwHPAAAAAAAAAAAAAAAAAAAAAAAAAAC5ByIAAI2FGACyAAAAAAAA)format('truetype')%3Bfont-weight%3Anormal%3Bfont-style%3Anormal%3B%7D%40font-face%7Bfont-family%3A'math1122e6b39e850bce62e39ea338f'%3Bsrc%3Aurl(data%3Afont%2Ftruetype%3Bcharset%3Dutf-8%3Bbase64%2CAAEAAAAMAIAAAwBAT1MvMi7iBBMAAADMAAAATmNtYXDEvmKUAAABHAAAADxjdnQgDVUNBwAAAVgAAAA6Z2x5ZoPi2VsAAAGUAAAA62hlYWQQC2qxAAACgAAAADZoaGVhCGsXSAAAArgAAAAkaG10eE2rRkcAAALcAAAADGxvY2EAHTwYAAAC6AAAABBtYXhwBT0FPgAAAvgAAAAgbmFtZaBxlY4AAAMYAAABn3Bvc3QB9wD6AAAEuAAAACBwcmVwa1uragAABNgAAAAUAAADSwGQAAUAAAQABAAAAAAABAAEAAAAAAAAAQEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAgICAAAAAg1UADev96AAAD6ACWAAAAAAACAAEAAQAAABQAAwABAAAAFAAEACgAAAAGAAQAAQACACsiEv%2F%2FAAAAKyIS%2F%2F%2F%2F1t3wAAEAAAAAAAAAAAFUAywAgAEAAFYAKgJYAh4BDgEsAiwAWgGAAoAAoADUAIAAAAAAAAAAKwBVAIAAqwDVAQABKwAHAAAAAgBVAAADAAOrAAMABwAAMxEhESUhESFVAqv9qwIA%2FgADq%2FxVVQMAAAEAgABVAtUCqwALAEkBGLIMAQEUExCxAAP2sQEE9bAKPLEDBfWwCDyxBQT1sAY8sQ0D5gCxAAATELEBBuSxAQETELAFPLEDBOWxCwX1sAc8sQkE5TEwEyERMxEhFSERIxEhgAEAVQEA%2FwBV%2FwABqwEA%2FwBW%2FwABAAABAIABVQLVAasAAwAwGAGwBBCxAAP2sAM8sQIH9bABPLEFA%2BYAsQAAExCxAAblsQABExCwATyxAwX1sAI8EyEVIYACVf2rAatWAAABAAAAAQAA1XjOQV8PPPUAAwQA%2F%2F%2F%2F%2F9Y6E3P%2F%2F%2F%2F%2F1joTcwAA%2FyAEgAOrAAAACgACAAEAAAAAAAEAAAPo%2F2oAABdwAAD%2FtgSAAAEAAAAAAAAAAAAAAAAAAAADA1IAVQNWAIADVgCAAAAAAAAAACgAAAChAAAA6wABAAAAAwBeAAUAAAAAAAIAgAQAAAAAAAQAAN4AAAAAAAAAFQECAAAAAAAAAAEAEgAAAAAAAAAAAAIADgASAAAAAAAAAAMAMAAgAAAAAAAAAAQAEgBQAAAAAAAAAAUAFgBiAAAAAAAAAAYACQB4AAAAAAAAAAgAHACBAAEAAAAAAAEAEgAAAAEAAAAAAAIADgASAAEAAAAAAAMAMAAgAAEAAAAAAAQAEgBQAAEAAAAAAAUAFgBiAAEAAAAAAAYACQB4AAEAAAAAAAgAHACBAAMAAQQJAAEAEgAAAAMAAQQJAAIADgASAAMAAQQJAAMAMAAgAAMAAQQJAAQAEgBQAAMAAQQJAAUAFgBiAAMAAQQJAAYACQB4AAMAAQQJAAgAHACBAE0AYQB0AGgAIABGAG8AbgB0AFIAZQBnAHUAbABhAHIATQBhAHQAaABzACAARgBvAHIAIABNAG8AcgBlACAATQBhAHQAaAAgAEYAbwBuAHQATQBhAHQAaAAgAEYAbwBuAHQAVgBlAHIAcwBpAG8AbgAgADEALgAwTWF0aF9Gb250AE0AYQB0AGgAcwAgAEYAbwByACAATQBvAHIAZQAAAwAAAAAAAAH0APoAAAAAAAAAAAAAAAAAAAAAAAAAALkHEQAAjYUYALIAAAAVFBOxAAE%2F)format('truetype')%3Bfont-weight%3Anormal%3Bfont-style%3Anormal%3B%7D%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cline%20stroke%3D%22%23000000%22%20stroke-linecap%3D%22square%22%20stroke-width%3D%221%22%20x1%3D%222.5%22%20x2%3D%2278.5%22%20y1%3D%2224.5%22%20y2%3D%2224.5%22%2F%3E%3Ctext%20font-family%3D%22ae2ef524fbf3d9fe611d5a8e90fefdc%22%20font-size%3D%2216%22%20font-style%3D%22italic%22%20text-anchor%3D%22middle%22%20x%3D%2236.5%22%20y%3D%2218%22%3Ea%3C%2Ftext%3E%3Ctext%20font-family%3D%22Arial%22%20font-size%3D%2212%22%20text-anchor%3D%22middle%22%20x%3D%2245.5%22%20y%3D%2211%22%3E4%3C%2Ftext%3E%3Ctext%20font-family%3D%22ae2ef524fbf3d9fe611d5a8e90fefdc%22%20font-size%3D%2216%22%20font-style%3D%22italic%22%20text-anchor%3D%22middle%22%20x%3D%228.5%22%20y%3D%2244%22%3Ea%3C%2Ftext%3E%3Ctext%20font-family%3D%22Arial%22%20font-size%3D%2212%22%20text-anchor%3D%22middle%22%20x%3D%2217.5%22%20y%3D%2237%22%3E8%3C%2Ftext%3E%3Ctext%20font-family%3D%22math1122e6b39e850bce62e39ea338f%22%20font-size%3D%2216%22%20text-anchor%3D%22middle%22%20x%3D%2228.5%22%20y%3D%2244%22%3E%26%23x2212%3B%3C%2Ftext%3E%3Ctext%20font-family%3D%22ae2ef524fbf3d9fe611d5a8e90fefdc%22%20font-size%3D%2216%22%20font-style%3D%22italic%22%20text-anchor%3D%22middle%22%20x%3D%2240.5%22%20y%3D%2244%22%3Ea%3C%2Ftext%3E%3Ctext%20font-family%3D%22Arial%22%20font-size%3D%2212%22%20text-anchor%3D%22middle%22%20x%3D%2249.5%22%20y%3D%2237%22%3E4%3C%2Ftext%3E%3Ctext%20font-family%3D%22math1122e6b39e850bce62e39ea338f%22%20font-size%3D%2216%22%20text-anchor%3D%22middle%22%20x%3D%2260.5%22%20y%3D%2244%22%3E%2B%3C%2Ftext%3E%3Ctext%20font-family%3D%22Arial%22%20font-size%3D%2216%22%20text-anchor%3D%22middle%22%20x%3D%2272.5%22%20y%3D%2244%22%3E1%3C%2Ftext%3E%3C%2Fsvg%3E\\\" style=\\\"height:49px; width:82px\\\" />&nbsp;এর মান কত?</p>";
//        String str1_parsed = String.valueOf(Html.fromHtml(str1));
//
//        Log.d("riririririri", "str1: " + str1);
//        Log.d("riririririri", "str1_parsed: " + str1_parsed);
//
////        WebView webview1 = root.findViewById(R.id.webview1);
////        webview1.getSettings().setJavaScriptEnabled(true);
////        webview1.loadData(str1_parsed, "text/html", "UTF-8");
//
//        TextView text1 = root.findViewById(R.id.text1);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            text1.setText(Html.fromHtml(str1, Html.FROM_HTML_MODE_COMPACT, new HtmlImageGetter(getActivity(), text1), null));
//        } else {
//            text1.setText(Html.fromHtml(str1, new HtmlImageGetter(getActivity(), text1), null));
//        }
//    }

    private void initView(View view) {
        tv_name = view.findViewById(R.id.tv_name);
        tv_bmdc_no = view.findViewById(R.id.tv_bmdc_no);

        tv_name.setText(SharedData.getUSER_NAME(getContext()));
        tv_bmdc_no.setText("BMDC NO: " + SharedData.getBMDC_NO(getContext()));

        linear_exam = view.findViewById(R.id.linear_exam);
        tv_more = view.findViewById(R.id.tv_more);

        linear_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                intent.putExtra("from_where", "MAIN");
                startActivity(intent);
            }
        });

        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_moreFragment);
            }
        });
    }
}