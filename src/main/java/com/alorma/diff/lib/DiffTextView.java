package com.alorma.diff.lib;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Bernat on 22/12/2014.
 */
public class DiffTextView extends TextView {

	private int additionColor;
	private int deletionColor;
	private int maxLines = -1;

	public DiffTextView(Context context) {
		super(context);
		init();
	}

	public DiffTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DiffTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public DiffTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init() {
		isInEditMode();

		additionColor = Color.parseColor("#CCFFCC");
		deletionColor = Color.parseColor("#FFDDDD");
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		if (!TextUtils.isEmpty(text) && type == BufferType.NORMAL) {
			String diff = text.toString();
			String[] split = diff.split("\\r?\\n|\\r");

			if (split.length > 0) {
				SpannableStringBuilder builder = new SpannableStringBuilder();

				int lines;
				if (maxLines > 0) {
					lines = Math.min(maxLines, split.length);
				} else {
					lines = split.length;
				}

				for (int i = 0; i < lines; i++) {
					String token = split[i];
					if (!token.startsWith("@@")) {
						if (i < (lines - 1)) {
							token = token.concat("\n");
						}
						BackgroundColorSpan span = null;
						SpannableString spannableDiff = new SpannableString(token);

						char firstChar = token.charAt(0);

						int color = 0;
						if (firstChar == '+') {
							color = additionColor;
						} else if (firstChar == '-') {
							color = deletionColor;
						}

						spannableDiff = new SpannableString(token);
						if (color == additionColor || color == deletionColor) {
							span = new BackgroundColorSpan(color);
							spannableDiff.setSpan(span, 0, token.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
						}

						builder.append(spannableDiff);
					}
				}

				super.setText(builder, type);
			}
		} else {
			super.setText(text, type);
		}
	}

	@Override
	public int getMaxLines() {
		return maxLines;
	}

	@Override
	public void setMaxLines(int maxlines) {
		this.maxLines = maxlines;
	}
}
