package net.hockeyapp.android.views;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * <h3>Description</h3>
 * 
 * A multi-row layout doing a line break when content doesn't fit into current row.
 * 
 * <h3>License</h3>
 * 
 * <pre>
 * Copyright (c) 2011-2014 Bit Stadium GmbH
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * </pre>
 *
 * @author Patrick Eschenbach
 */
public class AttachmentListView extends ViewGroup {

  private int line_height;

  public AttachmentListView(Context context) {
    super(context);
  }

  /**
   * Returns a list of Uris of its AttachmentView children.
   *
   * @return ArrayList of Uri
   */
  public ArrayList<Uri> getAttachments() {
    ArrayList<Uri> attachments = new ArrayList<Uri>();

    for (int i = 0; i < getChildCount(); i++) {
      AttachmentView attachmentView = (AttachmentView) getChildAt(i);
      attachments.add(attachmentView.getAttachmentUri());
    }
    return attachments;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    assert (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED);

    final int width = MeasureSpec.getSize(widthMeasureSpec);
    final int count = getChildCount();
    int height = 0;
    int line_height = 0;

    int xPos = getPaddingLeft();
    int yPos = getPaddingTop();

    for (int i = 0; i < count; i++) {
      final View child = getChildAt(i);
      AttachmentView attachmentView = (AttachmentView) child;
      height = attachmentView.getEffectiveMaxHeight() + attachmentView.getPaddingTop();

      if (child.getVisibility() != GONE) {
        final LayoutParams lp = child.getLayoutParams();
        child.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));

        final int childWidth = child.getMeasuredWidth();
        line_height = Math.max(line_height, child.getMeasuredHeight() + lp.height);

        if (xPos + childWidth > width) {
          xPos = getPaddingLeft();
          yPos += line_height;
        }
        xPos += childWidth + lp.width;
      }
    }
    this.line_height = line_height;

    if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
      height = yPos + line_height + getPaddingBottom();

    } else if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
      if (yPos + line_height + getPaddingBottom() < height) {
        height = yPos + line_height + getPaddingBottom();
      }
    }
    setMeasuredDimension(width, height);
  }

  @Override
  protected LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams(1, 1); // default of 1px spacing
  }

  @Override
  protected boolean checkLayoutParams(LayoutParams p) {
    return (p instanceof LayoutParams);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    final int count = getChildCount();
    final int width = r - l;
    int xPos = getPaddingLeft();
    int yPos = getPaddingTop();

    for (int i = 0; i < count; i++) {
      final View child = getChildAt(i);

      if (child.getVisibility() != GONE) {
        child.invalidate();
        final int childWidth = child.getMeasuredWidth();
        final int childHeight = child.getMeasuredHeight();

        final LayoutParams lp = child.getLayoutParams();
        if (xPos + childWidth > width) {
          xPos = getPaddingLeft();
          yPos += line_height;
        }
        child.layout(xPos, yPos, xPos + childWidth, yPos + childHeight);
        xPos += childWidth + lp.width + ((AttachmentView) child).getGap();
      }
    }
  }
}
