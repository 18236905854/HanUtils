package com.hanbo.utils.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.google.android.material.resources.MaterialResources;
import com.hanbo.utils.Interface.OnClickableSpanListener;
import com.hanbo.utils.Interface.OnResultListener;
import com.hanbo.utils.R;
import com.hanbo.utils.utils.EditTextUtil;
import com.hanbo.utils.utils.GlideUtils;
import com.hanbo.utils.utils.StringUtils;
import com.hanbo.utils.utils.TextViewUtil;

/**
 * Function:
 *
 * @since 2020/4/15
 */
public class MySelectClickView extends LinearLayout {

    private final Context mContext;
    private final LinearLayout mLlSelectClick;
    private final LinearLayout mLLSelectClickLayout;
    private final ImageView mIvSelectClickLeftImg;
    private final TextView mTvSelectClickLeftText;
    private final LinearLayout mLlSelectClickTitle;
    private final TextView mTvSelectClickTitle;
    private final TextView mTvSelectClickTip;
    private final EditText mEtSelectClickMsg;
    private final RelativeLayout mRlSelectClickMsg;
    private final Switch mSwSelectClickOn;
    private final ImageView mIvSelectClickRightImg;
    private final TextView mTvSelectClickMsg;
    private final CheckBox mCbSelectClickOn;
    private final ImageView mIvSelectClickArrow;
    private final View mVSelectClickLineTop;
    private final View mVSelectClickLineBottom;
    private final View mVSelectClickLineLeft;
    private final View mVSelectClickLineRight;

    private final int defValue = -1;

    private Drawable bgRes;
    private int bgGravity;
    private int bgPadding;
    private int bgPaddingLeft;
    private int bgPaddingTop;
    private int bgPaddingRight;
    private int bgPaddingBottom;

    private Drawable imgLeftRes;
    private int imgLeftWidth;
    private int imgLeftHeight;
    private int imgLeftMinWidth;
    private int imgLeftMinHeight;
    private int imgLeftMargin;
    private int imgLeftMarginLeft;
    private int imgLeftMarginTop;
    private int imgLeftMarginRight;
    private int imgLeftMarginBottom;
    private boolean imgLeftShow;

    private int leftTextColor;
    private int leftTextRegexColor;
    private int leftTextMargin;
    private int leftTextMarginLeft;
    private int leftTextMarginTop;
    private int leftTextMarginRight;
    private int leftTextMarginBottom;
    private int leftTextStyle;
    private float leftTextSize;
    private String leftText;
    private String leftTextRegex;
    private boolean leftTextShow = true;


    private Drawable titleLayoutBgRes;
    private int titleLayoutBgGravity;
    private int titleLayoutBgPadding;
    private int titleLayoutBgPaddingLeft;
    private int titleLayoutBgPaddingTop;
    private int titleLayoutBgPaddingRight;
    private int titleLayoutBgPaddingBottom;

    private Drawable titleBgRes;
    private int titleWidth;
    private int titleHeight;
    private int titleMinWidth;
    private int titleMinHeight;
    private int titleColor;
    private int titleHintColor;
    private int titleRegexColor;
    private int titleMargin;
    private int titleMarginLeft;
    private int titleMarginTop;
    private int titleMarginRight;
    private int titleMarginBottom;
    private int titleStyle;
    private int titleEllipsize;
    private int titleMaxLines;
    private int titleMaxLength;
    private int titleMaxEms;
    private int titleGravity;
    private float titleSize;
    private String title;
    private String titleHint;
    private String titleRegex;
    private boolean titleShow = true;

    private Drawable tipBgRes;
    private int tipColor;
    private int tipRegexColor;
    private int tipWidth;
    private int tipHeight;
    private int tipMinWidth;
    private int tipMinHeight;
    private int tipMaxWidth;
    private int tipMaxHeight;
    private int tipMargin;
    private int tipMarginLeft;
    private int tipMarginTop;
    private int tipMarginRight;
    private int tipMarginBottom;
    private int tipStyle;
    private float tipSize;
    private String tip;
    private String tipRegex;
    private boolean tipShow = false;

    private Drawable editBgRes;
    private int editColor;
    private int editHintColor;
    private int editWidth;
    private int editHeight;
    private int editMinWidth;
    private int editMinHeight;
    private int editMaxWidth;
    private int editMaxHeight;
    private int editMargin;
    private int editMarginLeft;
    private int editMarginTop;
    private int editMarginRight;
    private int editMarginBottom;
    private int editStyle;
    private int editEllipsize;
    private int editMaxLines;
    private int editMaxLength;
    private int editInputType;
    private int editImeOptions;
    private int editGravity;
    private float editSize;
    private String edit;
    private String editHint;
    private String editDigits;
    private boolean editShow = false;
    private boolean editEnabled = true;
    private boolean editFocusable = true;

    private int editRlMargin;
    private int editRlMarginLeft;
    private int editRlMarginTop;
    private int editRlMarginRight;
    private int editRlMarginBottom;
    private boolean editRlShow = false;

    private Drawable imgRightRes;
    private int imgRightWidth;
    private int imgRightHeight;
    private int imgRightMinWidth;
    private int imgRightMinHeight;
    private int imgRightMargin;
    private int imgRightMarginLeft;
    private int imgRightMarginTop;
    private int imgRightMarginRight;
    private int imgRightMarginBottom;
    private boolean imgRightShow;

    private boolean switchShow = false;
    private boolean switchChecked;
    private boolean switchEnabled;
    private int switchWidth;
    private int switchHeight;
    private int switchThumbRes;
    private int switchTrackRes;
    private int switchMargin;
    private int switchMarginLeft;
    private int switchMarginTop;
    private int switchMarginRight;
    private int switchMarginBottom;

    private Drawable textBgRes;
    private int textColor;
    private int textHintColor;
    private int textRegexColor;
    private int textWidth;
    private int textHeight;
    private int textMinWidth;
    private int textMinHeight;
    private int textMargin;
    private int textMarginLeft;
    private int textMarginTop;
    private int textMarginRight;
    private int textMarginBottom;
    private int textPadding;
    private int textPaddingLeft;
    private int textPaddingTop;
    private int textPaddingRight;
    private int textPaddingBottom;
    private int textStyle;
    private int textEllipsize;
    private int textMaxLines;
    private int textMaxLength;
    private int textMaxEms;
    private int textGravity;
    private float textSize;
    private String text;
    private String textHint;
    private String textRegex;
    private Boolean textShow = false;

    private int checkBoxWidth;
    private int checkBoxHeight;
    private int checkBoxSrc;
    private int checkBoxMargin;
    private int checkBoxMarginLeft;
    private int checkBoxMarginTop;
    private int checkBoxMarginRight;
    private int checkBoxMarginBottom;
    private Boolean checkBoxShow = false;
    private Boolean checkBoxChecked = false;
    private Boolean checkBoxEnabled = false;

    private int arrowWidth;
    private int arrowHeight;
    private int arrowSrc;
    private int arrowMargin;
    private int arrowMarginLeft;
    private int arrowMarginTop;
    private int arrowMarginRight;
    private int arrowMarginBottom;
    private Boolean arrowShow = false;

    private int lineWidth;
    private int lineHeight;
    private int lineMargin;
    private int lineMarginLeft;
    private int lineMarginTop;
    private int lineMarginRight;
    private int lineMarginBottom;
    private int lineRes;
    private int lineColor;
    private Boolean lineShow = false;
    private int lineGravity = 1;

    private OnResultListener<String> mResultListener;


    public MySelectClickView(Context context) {
        this(context, null);
    }

    public MySelectClickView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("RestrictedApi")
    public MySelectClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        mLlSelectClick = (LinearLayout) View.inflate(mContext, R.layout.view_select_click, this);

        mLLSelectClickLayout = mLlSelectClick.findViewById(R.id.ll_select_click_layout);
        mIvSelectClickLeftImg = mLlSelectClick.findViewById(R.id.iv_select_click_left_img);
        mTvSelectClickLeftText = mLlSelectClick.findViewById(R.id.tv_select_click_left_text);
        mLlSelectClickTitle = mLlSelectClick.findViewById(R.id.ll_select_click_title);
        mTvSelectClickTitle = mLlSelectClick.findViewById(R.id.tv_select_click_title);
        mTvSelectClickTip = mLlSelectClick.findViewById(R.id.tv_select_click_tip);
        mEtSelectClickMsg = mLlSelectClick.findViewById(R.id.et_select_click_msg);
        mRlSelectClickMsg = mLlSelectClick.findViewById(R.id.rl_select_click_msg);
        mIvSelectClickRightImg = mLlSelectClick.findViewById(R.id.iv_select_click_right_img);
        mSwSelectClickOn = mLlSelectClick.findViewById(R.id.sw_select_click_on);
        mTvSelectClickMsg = mLlSelectClick.findViewById(R.id.tv_select_click_msg);
        mCbSelectClickOn = mLlSelectClick.findViewById(R.id.cb_select_click_on);
        mIvSelectClickArrow = mLlSelectClick.findViewById(R.id.iv_select_click_arrow);

        mVSelectClickLineTop = mLlSelectClick.findViewById(R.id.v_select_click_line_top);
        mVSelectClickLineBottom = mLlSelectClick.findViewById(R.id.v_select_click_line_bottom);
        mVSelectClickLineLeft = mLlSelectClick.findViewById(R.id.v_select_click_line_left);
        mVSelectClickLineRight = mLlSelectClick.findViewById(R.id.v_select_click_line_right);
        mVSelectClickLineTop.setVisibility(GONE);
        mVSelectClickLineBottom.setVisibility(GONE);
        mVSelectClickLineLeft.setVisibility(GONE);
        mVSelectClickLineRight.setVisibility(GONE);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MySelectClickView, defStyleAttr, 0);

        try {
            bgRes = MaterialResources.getDrawable(context, array, R.styleable.MySelectClickView_select_bg_src);
            bgGravity = array.getInt(R.styleable.MySelectClickView_select_gravity, Gravity.CENTER_VERTICAL);
            bgPadding = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_bg_padding, defValue);
            bgPaddingLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_bg_paddingLeft, defValue);
            bgPaddingTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_bg_paddingTop, defValue);
            bgPaddingRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_bg_paddingRight, defValue);
            bgPaddingBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_bg_paddingBottom, defValue);

            imgLeftRes = MaterialResources.getDrawable(context, array, R.styleable.MySelectClickView_select_left_img_src);
            imgLeftShow = array.getBoolean(R.styleable.MySelectClickView_select_left_img_show, false);
            imgLeftWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_img_width, defValue);
            imgLeftHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_img_height, defValue);
            imgLeftMinWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_img_min_width, defValue);
            imgLeftMinHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_img_min_height, defValue);
            imgLeftMargin = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_img_margin, defValue);
            imgLeftMarginLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_img_marginLeft, defValue);
            imgLeftMarginTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_img_marginTop, defValue);
            imgLeftMarginRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_img_marginRight, defValue);
            imgLeftMarginBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_img_marginBottom, defValue);

            leftTextColor = array.getColor(R.styleable.MySelectClickView_select_left_text_color, getResources().getColor(R.color.color_343434));
            leftTextRegexColor = array.getColor(R.styleable.MySelectClickView_select_left_text_regex_color, getResources().getColor(R.color.color_343434));
            leftTextSize = array.getDimension(R.styleable.MySelectClickView_select_left_text_size, defValue);
            leftTextShow = array.getBoolean(R.styleable.MySelectClickView_select_left_text_show, false);
            leftTextMargin = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_text_margin, defValue);
            leftTextMarginLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_text_marginLeft, defValue);
            leftTextMarginTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_text_marginTop, defValue);
            leftTextMarginRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_text_marginRight, defValue);
            leftTextMarginBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_left_text_marginBottom, defValue);
            leftText = array.getString(R.styleable.MySelectClickView_select_left_text);
            leftTextRegex = array.getString(R.styleable.MySelectClickView_select_left_text_regex);
            leftTextStyle = array.getInt(R.styleable.MySelectClickView_select_left_textStyle, 0);

            titleLayoutBgRes = MaterialResources.getDrawable(context, array, R.styleable.MySelectClickView_select_title_layout_bg_src);
            titleLayoutBgGravity = array.getInt(R.styleable.MySelectClickView_select_title_layout_gravity, Gravity.CENTER_VERTICAL);
            titleLayoutBgPadding = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_layout_bg_padding, defValue);
            titleLayoutBgPaddingLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_layout_bg_paddingLeft, defValue);
            titleLayoutBgPaddingTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_layout_bg_paddingTop, defValue);
            titleLayoutBgPaddingRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_layout_bg_paddingRight, defValue);
            titleLayoutBgPaddingBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_layout_bg_paddingBottom, defValue);

            titleBgRes = MaterialResources.getDrawable(context, array, R.styleable.MySelectClickView_select_title_bg_src);
            titleWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_width, defValue);
            titleHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_height, defValue);
            titleMinWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_min_width, defValue);
            titleMinHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_min_height, defValue);
            titleColor = array.getColor(R.styleable.MySelectClickView_select_title_color, getResources().getColor(R.color.color_343434));
            titleHintColor = array.getColor(R.styleable.MySelectClickView_select_title_hint_color, getResources().getColor(R.color.color_B2B2B2));
            titleRegexColor = array.getColor(R.styleable.MySelectClickView_select_title_regex_color, getResources().getColor(R.color.color_343434));
            titleSize = array.getDimension(R.styleable.MySelectClickView_select_title_size, defValue);
            titleShow = array.getBoolean(R.styleable.MySelectClickView_select_title_show, true);
            titleMargin = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_margin, defValue);
            titleMarginLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_marginLeft, defValue);
            titleMarginTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_marginTop, defValue);
            titleMarginRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_marginRight, defValue);
            titleMarginBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_title_marginBottom, defValue);
            title = array.getString(R.styleable.MySelectClickView_select_title);
            titleRegex = array.getString(R.styleable.MySelectClickView_select_title_regex);
            titleHint = array.getString(R.styleable.MySelectClickView_select_title_hint);
            titleStyle = array.getInt(R.styleable.MySelectClickView_select_titleStyle, 0);
            titleEllipsize = array.getInt(R.styleable.MySelectClickView_select_title_ellipsize, 0);
            titleGravity = array.getInt(R.styleable.MySelectClickView_select_title_gravity, Gravity.CENTER_VERTICAL);
            titleMaxLines = array.getInt(R.styleable.MySelectClickView_select_title_maxLines, defValue);
            titleMaxLength = array.getInt(R.styleable.MySelectClickView_select_title_maxLength, defValue);
            titleMaxEms = array.getInt(R.styleable.MySelectClickView_select_title_maxEms, defValue);


            tipBgRes = MaterialResources.getDrawable(context, array, R.styleable.MySelectClickView_select_tip_bg_src);
            tipColor = array.getColor(R.styleable.MySelectClickView_select_tip_color, getResources().getColor(R.color.color_343434));
            tipRegexColor = array.getColor(R.styleable.MySelectClickView_select_tip_regex_color, getResources().getColor(R.color.color_343434));
            tipSize = array.getDimension(R.styleable.MySelectClickView_select_tip_size, defValue);
            tipShow = array.getBoolean(R.styleable.MySelectClickView_select_tip_show, false);
            tipWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_tip_width, defValue);
            tipHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_tip_height, defValue);
            tipMinWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_tip_min_width, defValue);
            tipMinHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_tip_min_height, defValue);
            tipMaxWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_tip_max_width, defValue);
            tipMaxHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_tip_max_height, defValue);
            tipMargin = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_tip_margin, defValue);
            tipMarginLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_tip_marginLeft, defValue);
            tipMarginTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_tip_marginTop, defValue);
            tipMarginRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_tip_marginRight, defValue);
            tipMarginBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_tip_marginBottom, defValue);
            tip = array.getString(R.styleable.MySelectClickView_select_tip);
            tipRegex = array.getString(R.styleable.MySelectClickView_select_tip_regex);
            tipStyle = array.getInt(R.styleable.MySelectClickView_select_tipStyle, 0);

            editBgRes = MaterialResources.getDrawable(context, array, R.styleable.MySelectClickView_select_edit_bg_src);
            editEnabled = array.getBoolean(R.styleable.MySelectClickView_select_edit_enabled, true);
            editFocusable = array.getBoolean(R.styleable.MySelectClickView_select_edit_focusable, true);
            editColor = array.getColor(R.styleable.MySelectClickView_select_edit_color, getResources().getColor(R.color.color_343434));
            editHintColor = array.getColor(R.styleable.MySelectClickView_select_edit_hint_color, getResources().getColor(R.color.color_B2B2B2));
            editSize = array.getDimension(R.styleable.MySelectClickView_select_edit_size, defValue);
            editShow = array.getBoolean(R.styleable.MySelectClickView_select_edit_show, false);
            editWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_width, defValue);
            editHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_height, defValue);
            editMinWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_min_width, defValue);
            editMinHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_min_height, defValue);
            editMaxWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_max_width, defValue);
            editMaxHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_max_height, defValue);
            editMargin = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_margin, defValue);
            editMarginLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_marginLeft, defValue);
            editMarginTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_marginTop, defValue);
            editMarginRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_marginRight, defValue);
            editMarginBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_marginBottom, defValue);
            edit = array.getString(R.styleable.MySelectClickView_select_edit);
            editHint = array.getString(R.styleable.MySelectClickView_select_edit_hint);
            editStyle = array.getInt(R.styleable.MySelectClickView_select_editStyle, 0);
            editEllipsize = array.getInt(R.styleable.MySelectClickView_select_edit_ellipsize, 0);
            editMaxLines = array.getInt(R.styleable.MySelectClickView_select_edit_maxLines, defValue);
            editMaxLength = array.getInt(R.styleable.MySelectClickView_select_edit_maxLength, defValue);
            editInputType = array.getInt(R.styleable.MySelectClickView_select_edit_inputType, InputType.TYPE_CLASS_TEXT);
            editImeOptions = array.getInt(R.styleable.MySelectClickView_select_edit_imeOptions, 0);
            editGravity = array.getInt(R.styleable.MySelectClickView_select_edit_gravity, Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            editDigits = array.getString(R.styleable.MySelectClickView_select_edit_digits);

            editRlShow = array.getBoolean(R.styleable.MySelectClickView_select_edit_rl_show, true);
            editRlMargin = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_rl_margin, defValue);
            editRlMarginLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_rl_marginLeft, defValue);
            editRlMarginTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_rl_marginTop, defValue);
            editRlMarginRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_rl_marginRight, defValue);
            editRlMarginBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_edit_rl_marginBottom, defValue);

            imgRightRes = MaterialResources.getDrawable(context, array, R.styleable.MySelectClickView_select_right_img_src);
            imgRightShow = array.getBoolean(R.styleable.MySelectClickView_select_right_img_show, false);
            imgRightWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_right_img_width, defValue);
            imgRightHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_right_img_height, defValue);
            imgRightMinWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_right_img_min_width, defValue);
            imgRightMinHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_right_img_min_height, defValue);
            imgRightMargin = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_right_img_margin, defValue);
            imgRightMarginLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_right_img_marginLeft, defValue);
            imgRightMarginTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_right_img_marginTop, defValue);
            imgRightMarginRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_right_img_marginRight, defValue);
            imgRightMarginBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_right_img_marginBottom, defValue);

            switchShow = array.getBoolean(R.styleable.MySelectClickView_select_switch_show, false);
            switchWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_checkbox_width, defValue);
            switchHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_checkbox_height, defValue);
            switchChecked = array.getBoolean(R.styleable.MySelectClickView_select_switch_checked, false);
            switchEnabled = array.getBoolean(R.styleable.MySelectClickView_select_switch_enabled, true);
            switchThumbRes = array.getResourceId(R.styleable.MySelectClickView_select_switch_thumb, R.drawable.switch_thumb_default);
            switchTrackRes = array.getResourceId(R.styleable.MySelectClickView_select_switch_track, R.drawable.switch_track_default);
            switchMargin = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_switch_margin, defValue);
            switchMarginLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_switch_marginLeft, defValue);
            switchMarginTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_switch_marginTop, defValue);
            switchMarginRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_switch_marginRight, defValue);
            switchMarginBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_switch_marginBottom, defValue);

            textBgRes = MaterialResources.getDrawable(context, array, R.styleable.MySelectClickView_select_text_bg_src);
            textColor = array.getColor(R.styleable.MySelectClickView_select_text_color, getResources().getColor(R.color.color_343434));
            textHintColor = array.getColor(R.styleable.MySelectClickView_select_text_hint_color, getResources().getColor(R.color.color_9D9D9D));
            textSize = array.getDimension(R.styleable.MySelectClickView_select_text_size, defValue);
            textWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_width, defValue);
            textHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_height, defValue);
            textMinWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_min_width, defValue);
            textMinHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_min_height, defValue);
            textMargin = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_margin, defValue);
            textMarginLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_marginLeft, defValue);
            textMarginTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_marginTop, defValue);
            textMarginRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_marginRight, defValue);
            textMarginBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_marginBottom, defValue);
            textPadding = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_padding, defValue);
            textPaddingLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_paddingLeft, defValue);
            textPaddingTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_paddingTop, defValue);
            textPaddingRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_paddingRight, defValue);
            textPaddingBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_text_paddingBottom, defValue);
            textShow = array.getBoolean(R.styleable.MySelectClickView_select_text_show, false);
            text = array.getString(R.styleable.MySelectClickView_select_text);
            textHint = array.getString(R.styleable.MySelectClickView_select_text_hint);
            textStyle = array.getInt(R.styleable.MySelectClickView_select_textStyle, 0);
            textEllipsize = array.getInt(R.styleable.MySelectClickView_select_text_ellipsize, 0);
            textMaxLines = array.getInt(R.styleable.MySelectClickView_select_text_maxLines, defValue);
            textMaxLength = array.getInt(R.styleable.MySelectClickView_select_text_maxLength, defValue);
            textMaxEms = array.getInt(R.styleable.MySelectClickView_select_text_maxEms, defValue);
            textGravity = array.getInt(R.styleable.MySelectClickView_select_text_gravity, Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            textRegex = array.getString(R.styleable.MySelectClickView_select_text_regex);
            textRegexColor = array.getColor(R.styleable.MySelectClickView_select_text_regex_color, getResources().getColor(R.color.color_343434));

            checkBoxWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_checkbox_width, defValue);
            checkBoxHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_checkbox_height, defValue);
            checkBoxSrc = array.getResourceId(R.styleable.MySelectClickView_select_checkbox_src, R.drawable.checkbox_bg_default);
            checkBoxShow = array.getBoolean(R.styleable.MySelectClickView_select_checkbox_show, false);
            checkBoxChecked = array.getBoolean(R.styleable.MySelectClickView_select_checkbox_checked, false);
            checkBoxEnabled = array.getBoolean(R.styleable.MySelectClickView_select_checkbox_enabled, true);
            checkBoxMargin = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_checkbox_margin, defValue);
            checkBoxMarginLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_checkbox_marginLeft, defValue);
            checkBoxMarginTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_checkbox_marginTop, defValue);
            checkBoxMarginRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_checkbox_marginRight, defValue);
            checkBoxMarginBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_checkbox_marginBottom, defValue);

            arrowWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_arrow_width, defValue);
            arrowHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_arrow_height, defValue);
            arrowSrc = array.getResourceId(R.styleable.MySelectClickView_select_arrow_src, R.mipmap.ic_arrow_right_gray);
            arrowShow = array.getBoolean(R.styleable.MySelectClickView_select_arrow_show, false);
            arrowMargin = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_arrow_margin, defValue);
            arrowMarginLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_arrow_marginLeft, defValue);
            arrowMarginTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_arrow_marginTop, defValue);
            arrowMarginRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_arrow_marginRight, defValue);
            arrowMarginBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_arrow_marginBottom, defValue);

            lineWidth = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_line_width, defValue);
            lineHeight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_line_height, defValue);
            lineMargin = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_line_margin, defValue);
            lineMarginLeft = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_line_marginLeft, defValue);
            lineMarginTop = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_line_marginTop, defValue);
            lineMarginRight = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_line_marginRight, defValue);
            lineMarginBottom = array.getDimensionPixelSize(R.styleable.MySelectClickView_select_line_marginBottom, defValue);
            lineRes = array.getResourceId(R.styleable.MySelectClickView_select_line_src, defValue);
            lineColor = array.getColor(R.styleable.MySelectClickView_select_line_src, getResources().getColor(R.color.color_DDDDDD));
            lineShow = array.getBoolean(R.styleable.MySelectClickView_select_line_show, false);
            lineGravity = array.getInt(R.styleable.MySelectClickView_select_line_gravity, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        array.recycle();

        setRefreshUI();
    }

    private void setRefreshUI() {
        /******************************************************* 背景 *******************************************************/

        if (bgRes != null) {
            mLLSelectClickLayout.setBackground(bgRes);
        }
        mLLSelectClickLayout.setGravity(bgGravity);

        if (bgPadding != defValue) {
            mLLSelectClickLayout.setPadding(bgPadding, bgPadding, bgPadding, bgPadding);
        }
        if (bgPaddingLeft != defValue || bgPaddingTop != defValue || bgPaddingRight != defValue || bgPaddingBottom != defValue) {
            mLLSelectClickLayout.setPadding(
                    bgPaddingLeft != defValue ? bgPaddingLeft : 0
                    , bgPaddingTop != defValue ? bgPaddingTop : 0
                    , bgPaddingRight != defValue ? bgPaddingRight : 0
                    , bgPaddingBottom != defValue ? bgPaddingBottom : 0);
        }

        /******************************************************* 左侧图标 *******************************************************/
        if (imgLeftRes != null) {
            mIvSelectClickLeftImg.setBackground(imgLeftRes);
        }
        if (imgLeftWidth != defValue) {
            ViewGroup.LayoutParams params = mIvSelectClickLeftImg.getLayoutParams();
            params.width = imgLeftWidth;
            mIvSelectClickLeftImg.setLayoutParams(params);
        }
        if (imgLeftHeight != defValue) {
            ViewGroup.LayoutParams params = mIvSelectClickLeftImg.getLayoutParams();
            params.height = imgLeftHeight;
            mIvSelectClickLeftImg.setLayoutParams(params);
        }
        if (imgLeftMinWidth != defValue) {
            ViewGroup.LayoutParams params = mIvSelectClickLeftImg.getLayoutParams();
            if (imgLeftMinWidth > params.width) {
                params.width = imgLeftMinWidth;
                mIvSelectClickLeftImg.setLayoutParams(params);
            }
        }
        if (imgLeftMinHeight != defValue) {
            ViewGroup.LayoutParams params = mIvSelectClickLeftImg.getLayoutParams();
            if (imgLeftMinHeight > params.height) {
                params.height = imgLeftMinHeight;
                mIvSelectClickLeftImg.setLayoutParams(params);
            }
        }
        if (imgLeftMargin != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mIvSelectClickLeftImg.getLayoutParams();
            p.setMargins(imgLeftMargin, imgLeftMargin, imgLeftMargin, imgLeftMargin);
            mIvSelectClickLeftImg.setLayoutParams(p);
        }
        if (imgLeftMarginLeft != defValue || imgLeftMarginTop != defValue || imgLeftMarginRight != defValue || imgLeftMarginBottom != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mIvSelectClickLeftImg.getLayoutParams();
            p.setMargins(
                    imgLeftMarginLeft != defValue ? imgLeftMarginLeft : 0
                    , imgLeftMarginTop != defValue ? imgLeftMarginTop : 0
                    , imgLeftMarginRight != defValue ? imgLeftMarginRight : 0
                    , imgLeftMarginBottom != defValue ? imgLeftMarginBottom : 0);
            mIvSelectClickLeftImg.setLayoutParams(p);
        }
        mIvSelectClickLeftImg.setVisibility(imgLeftShow ? VISIBLE : GONE);

        /******************************************************* 左侧文字 *******************************************************/

        if (leftTextSize != defValue) {
            mTvSelectClickLeftText.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
        }
        if (leftTextMargin != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mTvSelectClickLeftText.getLayoutParams();
            p.setMargins(leftTextMargin, leftTextMargin, leftTextMargin, leftTextMargin);
            mTvSelectClickLeftText.setLayoutParams(p);
        }
        if (leftTextMarginLeft != defValue || leftTextMarginTop != defValue || leftTextMarginRight != defValue || leftTextMarginBottom != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mTvSelectClickLeftText.getLayoutParams();
            p.setMargins(
                    leftTextMarginLeft != defValue ? leftTextMarginLeft : 0
                    , leftTextMarginTop != defValue ? leftTextMarginTop : 0
                    , leftTextMarginRight != defValue ? leftTextMarginRight : 0
                    , leftTextMarginBottom != defValue ? leftTextMarginBottom : 0);
            mTvSelectClickLeftText.setLayoutParams(p);
        }
        mTvSelectClickLeftText.setTextColor(leftTextColor);
        mTvSelectClickLeftText.setTypeface(Typeface.defaultFromStyle(leftTextStyle));
        mTvSelectClickLeftText.setVisibility(leftTextShow ? VISIBLE : GONE);
        mTvSelectClickLeftText.setText(leftText);
        setTextViewRegex(mTvSelectClickLeftText, leftText, leftTextRegex, leftTextRegexColor);


        /******************************************************* 标题背景 *******************************************************/

        if (titleLayoutBgRes != null) {
            mLlSelectClickTitle.setBackground(titleLayoutBgRes);
        }
        mLlSelectClickTitle.setGravity(titleLayoutBgGravity);

        if (titleLayoutBgPadding != defValue) {
            mLlSelectClickTitle.setPadding(titleLayoutBgPadding, titleLayoutBgPadding, titleLayoutBgPadding, titleLayoutBgPadding);
        }
        if (titleLayoutBgPaddingLeft != defValue || titleLayoutBgPaddingTop != defValue || titleLayoutBgPaddingRight != defValue || titleLayoutBgPaddingBottom != defValue) {
            mLlSelectClickTitle.setPadding(
                    titleLayoutBgPaddingLeft != defValue ? titleLayoutBgPaddingLeft : 0
                    , titleLayoutBgPaddingTop != defValue ? titleLayoutBgPaddingTop : 0
                    , titleLayoutBgPaddingRight != defValue ? titleLayoutBgPaddingRight : 0
                    , titleLayoutBgPaddingBottom != defValue ? titleLayoutBgPaddingBottom : 0);
        }


        /******************************************************* 标题 *******************************************************/
        if (titleBgRes != null) {
            mTvSelectClickTitle.setBackground(titleBgRes);
        }
        if (titleWidth != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickTitle.getLayoutParams();
            params.width = titleWidth;
            mTvSelectClickTitle.setLayoutParams(params);
        }
        if (titleHeight != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickTitle.getLayoutParams();
            params.height = titleHeight;
            mTvSelectClickTitle.setLayoutParams(params);
        }
        if (titleMinWidth != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickTitle.getLayoutParams();
            if (titleMinWidth > params.width) {
                params.width = titleMinWidth;
                mTvSelectClickTitle.setLayoutParams(params);
            }
        }
        if (titleMinHeight != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickTitle.getLayoutParams();
            if (titleMinHeight > params.height) {
                params.height = titleMinHeight;
                mTvSelectClickTitle.setLayoutParams(params);
            }
        }
        if (titleSize != defValue) {
            mTvSelectClickTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
        }
        if (titleMargin != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mTvSelectClickTitle.getLayoutParams();
            p.setMargins(titleMargin, titleMargin, titleMargin, titleMargin);
            mTvSelectClickTitle.setLayoutParams(p);
        }
        if (titleMarginLeft != defValue || titleMarginTop != defValue || titleMarginRight != defValue || titleMarginBottom != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mTvSelectClickTitle.getLayoutParams();
            p.setMargins(
                    titleMarginLeft != defValue ? titleMarginLeft : 0
                    , titleMarginTop != defValue ? titleMarginTop : 0
                    , titleMarginRight != defValue ? titleMarginRight : 0
                    , titleMarginBottom != defValue ? titleMarginBottom : 0);
            mTvSelectClickTitle.setLayoutParams(p);
        }
        if (titleMaxLines != defValue) {
            mTvSelectClickTitle.setMaxLines(titleMaxLines);
        }
        if (titleMaxLength != defValue) {
            mTvSelectClickTitle.setFilters(new InputFilter[]{new InputFilter.LengthFilter(titleMaxLength)});
        }
        if (titleMaxEms != defValue) {
            mTvSelectClickTitle.setMaxEms(titleMaxEms);
        }
        switch (titleEllipsize) {
            case 0:
                break;
            case 1:
                mTvSelectClickTitle.setEllipsize(TextUtils.TruncateAt.START);
                break;
            case 2:
                mTvSelectClickTitle.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                break;
            case 3:
                mTvSelectClickTitle.setEllipsize(TextUtils.TruncateAt.END);
                break;
            case 4:
                mTvSelectClickTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                break;
        }
        mTvSelectClickTitle.setGravity(titleGravity);
        mTvSelectClickTitle.setTypeface(Typeface.defaultFromStyle(titleStyle));
        mTvSelectClickTitle.setTextColor(titleColor);
        mTvSelectClickTitle.setVisibility(titleShow ? VISIBLE : GONE);
        mTvSelectClickTitle.setText(title);
        mTvSelectClickTitle.setHint(titleHint);
        mTvSelectClickTitle.setHintTextColor(titleHintColor);
        setTextViewRegex(mTvSelectClickTitle, title, titleRegex, titleRegexColor);

        /******************************************************* 提示 *******************************************************/

        if (tipBgRes != null) {
            mTvSelectClickTip.setBackground(tipBgRes);
        }
        if (tipWidth != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickTip.getLayoutParams();
            params.width = tipWidth;
            mTvSelectClickTip.setLayoutParams(params);
        }
        if (tipHeight != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickTip.getLayoutParams();
            params.height = tipHeight;
            mTvSelectClickTip.setLayoutParams(params);
        }
        if (tipMinWidth != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickTip.getLayoutParams();
            if (tipMinWidth > params.width) {
                params.width = tipMinWidth;
                mTvSelectClickTip.setLayoutParams(params);
            }
        }
        if (tipMinHeight != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickTip.getLayoutParams();
            if (tipMinHeight > params.height) {
                params.height = tipMinHeight;
                mTvSelectClickTip.setLayoutParams(params);
            }
        }
        if (tipMaxWidth != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickTip.getLayoutParams();
            if (tipMaxWidth < params.width) {
                params.width = tipMaxWidth;
                mTvSelectClickTip.setLayoutParams(params);
            }
        }
        if (tipMaxHeight != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickTip.getLayoutParams();
            if (tipMaxHeight < params.height) {
                params.height = tipMaxHeight;
                mTvSelectClickTip.setLayoutParams(params);
            }
        }
        if (tipSize != defValue) {
            mTvSelectClickTip.setTextSize(TypedValue.COMPLEX_UNIT_PX, tipSize);
        }
        if (tipMargin != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mTvSelectClickTip.getLayoutParams();
            p.setMargins(tipMargin, tipMargin, tipMargin, tipMargin);
            mTvSelectClickTip.setLayoutParams(p);
        }
        if (tipMarginLeft != defValue || tipMarginTop != defValue || tipMarginRight != defValue || tipMarginBottom != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mTvSelectClickTip.getLayoutParams();
            p.setMargins(
                    tipMarginLeft != defValue ? tipMarginLeft : 0
                    , tipMarginTop != defValue ? tipMarginTop : 0
                    , tipMarginRight != defValue ? tipMarginRight : 0
                    , tipMarginBottom != defValue ? tipMarginBottom : 0);
            mTvSelectClickTip.setLayoutParams(p);
        }
        mTvSelectClickTip.setTextColor(tipColor);
        mTvSelectClickTip.setTypeface(Typeface.defaultFromStyle(tipStyle));
        mTvSelectClickTip.setVisibility(tipShow ? VISIBLE : GONE);
        mTvSelectClickTip.setText(tip);
        setTextViewRegex(mTvSelectClickTip, tip, tipRegex, tipRegexColor);

        /******************************************************* 输入框 *******************************************************/
        if (editBgRes != null) {
            mEtSelectClickMsg.setBackground(editBgRes);
        }
        if (editWidth != defValue) {
            ViewGroup.LayoutParams params = mEtSelectClickMsg.getLayoutParams();
            params.width = editWidth;
            mEtSelectClickMsg.setLayoutParams(params);
        }
        if (editHeight != defValue) {
            ViewGroup.LayoutParams params = mEtSelectClickMsg.getLayoutParams();
            params.height = editHeight;
            mEtSelectClickMsg.setLayoutParams(params);
        }
        if (editMinWidth != defValue) {
            ViewGroup.LayoutParams params = mEtSelectClickMsg.getLayoutParams();
            if (editMinWidth > params.width) {
                params.width = editMinWidth;
                mEtSelectClickMsg.setLayoutParams(params);
            }
        }
        if (editMinHeight != defValue) {
            ViewGroup.LayoutParams params = mEtSelectClickMsg.getLayoutParams();
            if (editMinHeight > params.height) {
                params.height = editMinHeight;
                mEtSelectClickMsg.setLayoutParams(params);
            }
        }
        if (editMaxWidth != defValue) {
            ViewGroup.LayoutParams params = mEtSelectClickMsg.getLayoutParams();
            if (editMaxWidth < params.width) {
                params.width = editMaxWidth;
                mEtSelectClickMsg.setLayoutParams(params);
            }
        }
        if (editMaxHeight != defValue) {
            ViewGroup.LayoutParams params = mEtSelectClickMsg.getLayoutParams();
            if (editMaxHeight < params.height) {
                params.height = editMaxHeight;
                mEtSelectClickMsg.setLayoutParams(params);
            }
        }
        if (editSize != defValue) {
            mEtSelectClickMsg.setTextSize(TypedValue.COMPLEX_UNIT_PX, editSize);
        }
        if (editMargin != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mEtSelectClickMsg.getLayoutParams();
            p.setMargins(editMargin, editMargin, editMargin, editMargin);
            mEtSelectClickMsg.setLayoutParams(p);
        }
        if (editMarginLeft != defValue || editMarginTop != defValue || editMarginRight != defValue || editMarginBottom != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mEtSelectClickMsg.getLayoutParams();
            p.setMargins(
                    editMarginLeft != defValue ? editMarginLeft : 0
                    , editMarginTop != defValue ? editMarginTop : 0
                    , editMarginRight != defValue ? editMarginRight : 0
                    , editMarginBottom != defValue ? editMarginBottom : 0);
            mEtSelectClickMsg.setLayoutParams(p);
        }
        if (editMaxLines != defValue) {
            mEtSelectClickMsg.setMaxLines(editMaxLines);
        }
        if (editMaxLength != defValue) {
            mEtSelectClickMsg.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editMaxLength)});
        }
        if (!TextUtils.isEmpty(editDigits)) {
            mEtSelectClickMsg.setKeyListener(DigitsKeyListener.getInstance(editDigits));
        }
        switch (editEllipsize) {
            case 0:
                break;
            case 1:
                mEtSelectClickMsg.setEllipsize(TextUtils.TruncateAt.START);
                break;
            case 2:
                mEtSelectClickMsg.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                break;
            case 3:
                mEtSelectClickMsg.setEllipsize(TextUtils.TruncateAt.END);
                break;
            case 4:
                mEtSelectClickMsg.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                break;
        }
        if (editInputType != defValue) {
            switch (editInputType) {
                case 0x99990001:
                    InputFilter[] priceFilters = {new AmountInputFilter()};
                    setInputFilters(priceFilters);
                    break;
                default:
                    mEtSelectClickMsg.setInputType(editInputType);
                    break;
            }
        }
        mEtSelectClickMsg.setTextColor(editColor);
        mEtSelectClickMsg.setHintTextColor(editHintColor);
        mEtSelectClickMsg.setGravity(editGravity);
        mEtSelectClickMsg.setTypeface(Typeface.defaultFromStyle(editStyle));
        mEtSelectClickMsg.setImeOptions(editImeOptions);
        mEtSelectClickMsg.setVisibility(editShow ? VISIBLE : GONE);
        mEtSelectClickMsg.setText(edit);
        mEtSelectClickMsg.setHint(editHint);
        mRlSelectClickMsg.setFocusable(editFocusable);
        mEtSelectClickMsg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (editEnabled) {
                    return v.onTouchEvent(event);
                } else {
                    return MySelectClickView.this.onTouchEvent(event);
                }
            }
        });


        if (editRlMargin != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mRlSelectClickMsg.getLayoutParams();
            p.setMargins(editRlMargin, editRlMargin, editRlMargin, editRlMargin);
            mRlSelectClickMsg.setLayoutParams(p);
        }
        if (editRlMarginLeft != defValue || editRlMarginTop != defValue || editRlMarginRight != defValue || editRlMarginBottom != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mRlSelectClickMsg.getLayoutParams();
            p.setMargins(
                    editRlMarginLeft != defValue ? editRlMarginLeft : 0
                    , editRlMarginTop != defValue ? editRlMarginTop : 0
                    , editRlMarginRight != defValue ? editRlMarginRight : 0
                    , editRlMarginBottom != defValue ? editRlMarginBottom : 0);
            mRlSelectClickMsg.setLayoutParams(p);
        }
        mRlSelectClickMsg.setVisibility(editRlShow ? VISIBLE : GONE);
        /******************************************************* 右侧图标 *******************************************************/
        if (imgRightRes != null) {
            mIvSelectClickRightImg.setBackground(imgRightRes);
        }
        if (imgRightWidth != defValue) {
            ViewGroup.LayoutParams params = mIvSelectClickRightImg.getLayoutParams();
            params.width = imgRightWidth;
            mIvSelectClickRightImg.setLayoutParams(params);
        }
        if (imgRightHeight != defValue) {
            ViewGroup.LayoutParams params = mIvSelectClickRightImg.getLayoutParams();
            params.height = imgRightHeight;
            mIvSelectClickRightImg.setLayoutParams(params);
        }
        if (imgRightMinWidth != defValue) {
            ViewGroup.LayoutParams params = mIvSelectClickRightImg.getLayoutParams();
            if (imgRightMinWidth > params.width) {
                params.width = imgRightMinWidth;
                mIvSelectClickRightImg.setLayoutParams(params);
            }
        }
        if (imgRightMinHeight != defValue) {
            ViewGroup.LayoutParams params = mIvSelectClickRightImg.getLayoutParams();
            if (imgRightMinHeight > params.height) {
                params.height = imgRightMinHeight;
                mIvSelectClickRightImg.setLayoutParams(params);
            }
        }
        if (imgRightMargin != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mIvSelectClickRightImg.getLayoutParams();
            p.setMargins(imgRightMargin, imgRightMargin, imgRightMargin, imgRightMargin);
            mIvSelectClickRightImg.setLayoutParams(p);
        }
        if (imgRightMarginLeft != defValue || imgRightMarginTop != defValue || imgRightMarginRight != defValue || imgRightMarginBottom != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mIvSelectClickRightImg.getLayoutParams();
            p.setMargins(
                    imgRightMarginLeft != defValue ? imgRightMarginLeft : 0
                    , imgRightMarginTop != defValue ? imgRightMarginTop : 0
                    , imgRightMarginRight != defValue ? imgRightMarginRight : 0
                    , imgRightMarginBottom != defValue ? imgRightMarginBottom : 0);
            mIvSelectClickRightImg.setLayoutParams(p);
        }
        mIvSelectClickRightImg.setVisibility(imgRightShow ? VISIBLE : GONE);

        /******************************************************* Switch *******************************************************/
        if (switchWidth != defValue) {
            ViewGroup.LayoutParams params = mSwSelectClickOn.getLayoutParams();
            if (switchWidth != params.width) {
                params.width = switchWidth;
                mSwSelectClickOn.setLayoutParams(params);
            }
        }
        if (switchHeight != defValue) {
            ViewGroup.LayoutParams params = mSwSelectClickOn.getLayoutParams();
            if (switchHeight != params.height) {
                params.height = switchHeight;
                mSwSelectClickOn.setLayoutParams(params);
            }
        }
        if (switchMargin != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mSwSelectClickOn.getLayoutParams();
            p.setMargins(switchMargin, switchMargin, switchMargin, switchMargin);
            mSwSelectClickOn.setLayoutParams(p);
        }
        if (switchMarginLeft != defValue || switchMarginTop != defValue || switchMarginRight != defValue || switchMarginBottom != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mSwSelectClickOn.getLayoutParams();
            p.setMargins(
                    switchMarginLeft != defValue ? switchMarginLeft : 0
                    , switchMarginTop != defValue ? switchMarginTop : 0
                    , switchMarginRight != defValue ? switchMarginRight : 0
                    , switchMarginBottom != defValue ? switchMarginBottom : 0);
            mSwSelectClickOn.setLayoutParams(p);
        }

        mSwSelectClickOn.setThumbResource(switchThumbRes);
        mSwSelectClickOn.setTrackResource(switchTrackRes);
        mSwSelectClickOn.setChecked(switchChecked);
        mSwSelectClickOn.setVisibility(switchShow ? VISIBLE : GONE);
        mSwSelectClickOn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (switchEnabled) {
                    return v.onTouchEvent(event);
                } else {
                    return MySelectClickView.this.onTouchEvent(event);
                }
            }
        });


        /******************************************************* 右侧Text *******************************************************/
        if (textBgRes != null) {
            mTvSelectClickMsg.setBackground(textBgRes);
        }
        if (textSize != defValue) {
            mTvSelectClickMsg.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        if (textWidth != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickMsg.getLayoutParams();
            params.width = textWidth;
            mTvSelectClickMsg.setLayoutParams(params);
        }
        if (textHeight != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickMsg.getLayoutParams();
            params.height = textHeight;
            mTvSelectClickMsg.setLayoutParams(params);
        }
        if (textMinWidth != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickMsg.getLayoutParams();
            if (textMinWidth > params.width) {
                params.width = textMinWidth;
                mTvSelectClickMsg.setLayoutParams(params);
            }
        }
        if (textMinHeight != defValue) {
            ViewGroup.LayoutParams params = mTvSelectClickMsg.getLayoutParams();
            if (textMinHeight > params.height) {
                params.height = textMinHeight;
                mTvSelectClickMsg.setLayoutParams(params);
            }
        }

        if (textMargin != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mTvSelectClickMsg.getLayoutParams();
            p.setMargins(textMargin, textMargin, textMargin, textMargin);
            mTvSelectClickMsg.setLayoutParams(p);
        }
        if (textMarginLeft != defValue || textMarginTop != defValue || textMarginRight != defValue || textMarginBottom != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mTvSelectClickMsg.getLayoutParams();
            p.setMargins(
                    textMarginLeft != defValue ? textMarginLeft : 0
                    , textMarginTop != defValue ? textMarginTop : 0
                    , textMarginRight != defValue ? textMarginRight : 0
                    , textMarginBottom != defValue ? textMarginBottom : 0);
            mTvSelectClickMsg.setLayoutParams(p);
        }
        if (textPadding != defValue) {
            mTvSelectClickMsg.setPadding(textPadding, textPadding, textPadding, textPadding);
        }
        if (textPaddingLeft != defValue || textPaddingTop != defValue || textPaddingRight != defValue || textPaddingBottom != defValue) {
            mTvSelectClickMsg.setPadding(
                    textPaddingLeft != defValue ? textPaddingLeft : 0
                    , textPaddingTop != defValue ? textPaddingTop : 0
                    , textPaddingRight != defValue ? textPaddingRight : 0
                    , textPaddingBottom != defValue ? textPaddingBottom : 0);
        }
        if (textMaxLines != defValue) {
            mTvSelectClickMsg.setMaxLines(textMaxLines);
        }
        if (textMaxLength != defValue) {
            mTvSelectClickMsg.setFilters(new InputFilter[]{new InputFilter.LengthFilter(textMaxLength)});
        }
        if (textMaxEms != defValue) {
            mTvSelectClickMsg.setMaxEms(textMaxEms);
        }
        switch (textEllipsize) {
            case 0:
                break;
            case 1:
                mTvSelectClickMsg.setEllipsize(TextUtils.TruncateAt.START);
                break;
            case 2:
                mTvSelectClickMsg.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                break;
            case 3:
                mTvSelectClickMsg.setEllipsize(TextUtils.TruncateAt.END);
                break;
            case 4:
                mTvSelectClickMsg.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                break;
        }

        mTvSelectClickMsg.setGravity(textGravity);
        mTvSelectClickMsg.setTextColor(textColor);
        mTvSelectClickMsg.setHintTextColor(textHintColor);
        mTvSelectClickMsg.setTypeface(Typeface.defaultFromStyle(textStyle));
        mTvSelectClickMsg.setVisibility(textShow ? VISIBLE : GONE);
        mTvSelectClickMsg.setText(text);
        mTvSelectClickMsg.setHint(textHint);
        setTextViewRegex(mTvSelectClickMsg, text, textRegex, textRegexColor);

        /******************************************************* 右侧CheckBox *******************************************************/

        if (checkBoxWidth != defValue) {
            ViewGroup.LayoutParams params = mCbSelectClickOn.getLayoutParams();
            if (checkBoxWidth != params.width) {
                params.width = checkBoxWidth;
                mCbSelectClickOn.setLayoutParams(params);
            }
        }
        if (checkBoxHeight != defValue) {
            ViewGroup.LayoutParams params = mCbSelectClickOn.getLayoutParams();
            if (checkBoxHeight != params.height) {
                params.height = checkBoxHeight;
                mCbSelectClickOn.setLayoutParams(params);
            }
        }
        if (checkBoxMargin != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mCbSelectClickOn.getLayoutParams();
            p.setMargins(checkBoxMargin, checkBoxMargin, checkBoxMargin, checkBoxMargin);
            mCbSelectClickOn.setLayoutParams(p);
        }
        if (checkBoxMarginLeft != defValue || checkBoxMarginTop != defValue || checkBoxMarginRight != defValue || checkBoxMarginBottom != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mCbSelectClickOn.getLayoutParams();
            p.setMargins(checkBoxMarginLeft, checkBoxMarginTop, checkBoxMarginRight, checkBoxMarginBottom);
            p.setMargins(
                    checkBoxMarginLeft != defValue ? checkBoxMarginLeft : 0
                    , checkBoxMarginTop != defValue ? checkBoxMarginTop : 0
                    , checkBoxMarginRight != defValue ? checkBoxMarginRight : 0
                    , checkBoxMarginBottom != defValue ? checkBoxMarginBottom : 0);
            mCbSelectClickOn.setLayoutParams(p);
        }
        mCbSelectClickOn.setOnTouchListener((v, event) -> {
            if (checkBoxEnabled) {
                return v.onTouchEvent(event);
            } else {
                return MySelectClickView.this.onTouchEvent(event);
            }
        });
        mCbSelectClickOn.setChecked(checkBoxChecked);
        mCbSelectClickOn.setVisibility(checkBoxShow ? VISIBLE : GONE);
        mCbSelectClickOn.setBackground(getResources().getDrawable(checkBoxSrc));

        /******************************************************* 右侧箭头 *******************************************************/
        if (arrowWidth != defValue) {
            ViewGroup.LayoutParams params = mIvSelectClickArrow.getLayoutParams();
            if (arrowWidth != params.width) {
                params.width = arrowWidth;
                mIvSelectClickArrow.setLayoutParams(params);
            }
        }
        if (arrowHeight != defValue) {
            ViewGroup.LayoutParams params = mIvSelectClickArrow.getLayoutParams();
            if (arrowHeight != params.height) {
                params.height = arrowHeight;
                mIvSelectClickArrow.setLayoutParams(params);
            }
        }
        if (arrowMargin != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mIvSelectClickArrow.getLayoutParams();
            p.setMargins(arrowMargin, arrowMargin, arrowMargin, arrowMargin);
            mIvSelectClickArrow.setLayoutParams(p);
        }
        if (arrowMarginLeft != defValue || arrowMarginTop != defValue || arrowMarginRight != defValue || arrowMarginBottom != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mIvSelectClickArrow.getLayoutParams();
            p.setMargins(
                    arrowMarginLeft != defValue ? arrowMarginLeft : 0
                    , arrowMarginTop != defValue ? arrowMarginTop : 0
                    , arrowMarginRight != defValue ? arrowMarginRight : 0
                    , arrowMarginBottom != defValue ? arrowMarginBottom : 0);
            mIvSelectClickArrow.setLayoutParams(p);
        }
        mIvSelectClickArrow.setVisibility(arrowShow ? VISIBLE : GONE);
        mIvSelectClickArrow.setImageResource(arrowSrc);

        /******************************************************* 分割线 *******************************************************/

        switch (lineGravity) {
            case 0:
                setLineSetting(mVSelectClickLineTop);
                break;
            case 1:
                setLineSetting(mVSelectClickLineBottom);
                break;
            case 2:
                setLineSetting(mVSelectClickLineLeft);
                break;
            case 3:
                setLineSetting(mVSelectClickLineRight);
                break;
        }
    }

    //设置扩展文字
    private void setTextViewRegex(TextView textView, String text, String regex, @ColorRes int regexColor) {
        if (!StringUtils.isEmpty(regex)) {
            TextViewUtil.setSpannableString(textView, text, regex, new OnClickableSpanListener() {
                @Override
                public void onClick(@NonNull CharSequence result) {
                    if (mResultListener != null) {
                        mResultListener.onResult(result.toString());
                    }
                }

                @SuppressLint("ResourceAsColor")
                @Override
                public void updateDrawState(@NonNull CharSequence result, int position, TextPaint ds) {
                    try {
                        ds.setColor(regexColor);//设置颜色
                        ds.setFakeBoldText(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void setLineSetting(View mVSelectClickLine) {

        if (lineWidth != defValue) {
            ViewGroup.LayoutParams params = mVSelectClickLine.getLayoutParams();
            if (lineWidth != params.width) {
                params.width = lineWidth;
                mVSelectClickLine.setLayoutParams(params);
            }
        }
        if (lineHeight != defValue) {
            ViewGroup.LayoutParams params = mVSelectClickLine.getLayoutParams();
            if (lineHeight != params.height) {
                params.height = lineHeight;
                mVSelectClickLine.setLayoutParams(params);
            }
        }
        if (lineMargin != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mVSelectClickLine.getLayoutParams();
            p.setMargins(lineMargin, lineMargin, lineMargin, lineMargin);
            mVSelectClickLine.setLayoutParams(p);
        }
        if (lineMarginLeft != defValue || lineMarginTop != defValue || lineMarginRight != defValue || lineMarginBottom != defValue) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mVSelectClickLine.getLayoutParams();
            p.setMargins(
                    lineMarginLeft != defValue ? lineMarginLeft : 0
                    , lineMarginTop != defValue ? lineMarginTop : 0
                    , lineMarginRight != defValue ? lineMarginRight : 0
                    , lineMarginBottom != defValue ? lineMarginBottom : 0);
            mVSelectClickLine.setLayoutParams(p);
        }
        if (lineRes != defValue) {
            mVSelectClickLine.setBackgroundResource(lineRes);
        }
        mVSelectClickLine.setBackgroundColor(lineColor);
        mVSelectClickLine.setVisibility(lineShow ? VISIBLE : GONE);
    }

    public void setLineShow(boolean isShow) {
        lineShow = isShow;
        switch (lineGravity) {
            case 0:
                mVSelectClickLineTop.setVisibility(isShow ? VISIBLE : GONE);
                break;
            case 1:
                mVSelectClickLineBottom.setVisibility(isShow ? VISIBLE : GONE);
                break;
            case 2:
                mVSelectClickLineLeft.setVisibility(isShow ? VISIBLE : GONE);
                break;
            case 3:
                mVSelectClickLineRight.setVisibility(isShow ? VISIBLE : GONE);
                break;
        }
    }

    public void setLeftImgSrc(String res) {
        mIvSelectClickLeftImg.setBackgroundResource(R.color.transparent);
        GlideUtils.load(mContext, res, mIvSelectClickLeftImg);
    }

    public void setBgSrc(@ColorRes @DrawableRes int res) {
        mLLSelectClickLayout.setBackgroundResource(res);
    }

    public void setLeftImgCircleSrc(String res) {
        mIvSelectClickLeftImg.setBackgroundResource(R.color.transparent);
        GlideUtils.loadCircle(mContext, res, mIvSelectClickLeftImg);
    }

    public void setLeftImgSrc(@ColorRes @DrawableRes int res) {
        mIvSelectClickLeftImg.setImageResource(res);
    }

    public void setLeftImgShow(boolean isShow) {
        mIvSelectClickLeftImg.setVisibility(isShow ? VISIBLE : GONE);
    }

    public ImageView getLeftImgView() {
        return mIvSelectClickLeftImg;
    }

    public String getLeftText() {
        return mTvSelectClickLeftText.getText().toString().trim();
    }


    public void setLeftTextMsg(Object msg) {
        mTvSelectClickLeftText.setText(StringUtils.getString(msg));
    }

    public TextView getTitleView() {
        return mTvSelectClickTitle;
    }

    public String getTitleText() {
        return mTvSelectClickTitle.getText().toString().trim();
    }

    public void setTitleText(Object title) {
        mTvSelectClickTitle.setText(StringUtils.getString(title));
    }

    public void setTitleShow(boolean isShow) {
        mTvSelectClickTitle.setVisibility(isShow ? VISIBLE : GONE);
    }

    public void setTipText(Object title) {
        mTvSelectClickTip.setText(StringUtils.getString(title));
    }

    public void setTipBackgroundSrc(@DrawableRes int res) {
        mTvSelectClickTip.setBackgroundResource(res);
    }

    public void setTipShow(boolean isShow) {
        mTvSelectClickTip.setVisibility(isShow ? VISIBLE : GONE);
    }

    @SuppressLint("ResourceAsColor")
    public void setTitleColor(@ColorRes int colorRes) {
        mTvSelectClickTitle.setTextColor(mContext.getResources().getColor(colorRes));
    }

    public void setRightImgSrc(String res) {
        mIvSelectClickRightImg.setBackgroundResource(R.color.transparent);
        GlideUtils.load(mContext, res, mIvSelectClickRightImg);
    }

    public void setRightImgShow(boolean isShow) {
        mIvSelectClickRightImg.setVisibility(isShow ? VISIBLE : GONE);
    }

    public void setRightImgCircleSrc(String res) {
        mIvSelectClickRightImg.setBackgroundResource(R.color.transparent);
        GlideUtils.loadCircle(mContext, res, mIvSelectClickRightImg);
    }

    public void setRightImgSrc(int res) {
        GlideUtils.load(mContext, res, mIvSelectClickRightImg);
    }

    @SuppressLint("ResourceAsColor")
    public void setRightTextColor(@ColorRes int colorRes) {
        mTvSelectClickMsg.setTextColor(mContext.getResources().getColor(colorRes));
    }

    public void setRightTextMsg(Object msg) {
        mTvSelectClickMsg.setText(StringUtils.getString(msg));
    }

    public void setRightTextBgSrc(@ColorRes @DrawableRes int res) {
        mTvSelectClickMsg.setBackgroundResource(res);
    }

    public void setRightClickable(boolean clickable) {
        mTvSelectClickMsg.setClickable(clickable);
    }

    public void setRightHintMsg(String msg) {
        mTvSelectClickMsg.setHint(msg);
    }

    public String getRightTextMsg() {
        return mTvSelectClickMsg.getText().toString().trim();
    }

    public void setEditTextMsg(Object msg) {
        mEtSelectClickMsg.setText(StringUtils.getString(msg));
    }

    public EditText getEditView() {
        return mEtSelectClickMsg;
    }

    public EditText setEditEnabled(boolean enabled) {
        mEtSelectClickMsg.setEnabled(enabled);
        return mEtSelectClickMsg;
    }

    public String getEditTextMsg() {
        return mEtSelectClickMsg.getText().toString().trim();
    }

    public void setInputFilters(InputFilter[] filters) {
        if (filters == null) {
            throw new IllegalArgumentException();
        }
        mEtSelectClickMsg.setFilters(filters);
    }

    public EditText setInputType(int type) {
        mEtSelectClickMsg.setInputType(type);
        return mEtSelectClickMsg;
    }

    public void setRlEditShow(boolean isShow) {
        mRlSelectClickMsg.setVisibility(isShow ? VISIBLE : GONE);
    }

    public ImageView getArrowView() {
        return mIvSelectClickArrow;
    }

    public void setArrowSrc(@DrawableRes int arrowSrc) {
        mIvSelectClickArrow.setImageResource(arrowSrc);
    }

    public void setArrowShow(boolean isShow) {
        mIvSelectClickArrow.setVisibility(isShow ? VISIBLE : GONE);
    }

    public void addTextChangedListener(TextWatcher watcher) {
        mEtSelectClickMsg.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                mEtSelectClickMsg.addTextChangedListener(watcher);
            } else {
                mEtSelectClickMsg.removeTextChangedListener(watcher);
            }
        });
    }

    public void setOnEditResultsListener(OnResultListener<String> listener) {
        EditTextUtil.addTextChangedListener(mEtSelectClickMsg, listener);
    }

    public void setOnSwitchChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        if (listener != null) {
            mSwSelectClickOn.setOnCheckedChangeListener(listener);
        }
    }

    public boolean getSwitchChecked() {
        return mSwSelectClickOn.isChecked();
    }

    public void setSwitchChecked(boolean checked) {
        mSwSelectClickOn.setChecked(checked);
    }

    public void setOnCheckBoxChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        if (listener != null) {
            mCbSelectClickOn.setOnCheckedChangeListener(listener);
        }
    }

    public void setOnCheckBoxChangeListener(Object tag, CompoundButton.OnCheckedChangeListener listener) {
        if (listener != null) {
            mCbSelectClickOn.setTag(tag);
            mCbSelectClickOn.setOnCheckedChangeListener(listener);
        }
    }

    public boolean getCheckBoxChecked() {
        return mCbSelectClickOn.isChecked();
    }

    public void setCheckBoxChecked(boolean checked) {
        mCbSelectClickOn.setChecked(checked);
    }

    public void setBtnRegexClickListener(OnResultListener<String> listener) {
        this.mResultListener = listener;
    }

    public void setBtnClickListener(View.OnClickListener listener) {
        setBtnClickListener(null, listener);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setBtnClickListener(Object tag, View.OnClickListener listener) {
        if (listener != null) {
            this.setTag(tag);
            mIvSelectClickLeftImg.setTag(tag);
            mTvSelectClickLeftText.setTag(tag);
            mTvSelectClickTitle.setTag(tag);
            mTvSelectClickTip.setTag(tag);
            mEtSelectClickMsg.setTag(tag);
            mRlSelectClickMsg.setTag(tag);
            mIvSelectClickRightImg.setTag(tag);
            mTvSelectClickMsg.setTag(tag);
            mIvSelectClickArrow.setTag(tag);


            this.setOnClickListener(listener);
            mIvSelectClickLeftImg.setOnClickListener(listener);
            mIvSelectClickLeftImg.setOnClickListener(listener);
            mTvSelectClickLeftText.setOnClickListener(listener);
            mTvSelectClickTitle.setOnClickListener(listener);
            mTvSelectClickTip.setOnClickListener(listener);
            mEtSelectClickMsg.setOnClickListener(listener);
            mRlSelectClickMsg.setOnClickListener(listener);
            mIvSelectClickRightImg.setOnClickListener(listener);
            mTvSelectClickMsg.setOnClickListener(listener);
            mIvSelectClickArrow.setOnClickListener(listener);


            if (!switchEnabled) {
                mSwSelectClickOn.setTag(tag);
                mSwSelectClickOn.setOnTouchListener((v, event) -> {
                    mSwSelectClickOn.setOnClickListener(listener);
                    return false;
                });
            }
        }
    }
}
