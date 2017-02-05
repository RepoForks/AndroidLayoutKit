package io.fabianterhorst.layoutkit;

import android.graphics.Canvas;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabianterhorst on 03.02.17.
 */

public class BaseView {

    private View view;

    private Rect frame;

    private List<BaseView> subViews;

    public BaseView(Rect rect) {
        this(null, rect);
    }

    public BaseView(View view) {
        this(view, null);
    }

    public BaseView(View view, Rect frame) {
        this.view = view;
        this.frame = frame;
        //view.layout(0, 0, (int) frame.getWidth(), (int) frame.getHeight());
    }

    public void draw(Canvas canvas) {
        if (view != null) {
            canvas.save();
            canvas.translate(frame.getOrigin().getX(), frame.getOrigin().getY());
            view.draw(canvas);
            canvas.restore();
        }
        if (subViews == null) return;
        for (BaseView view : subViews) {
            view.draw(canvas);
        }
    }

    public void setFrame(Rect frame) {
        this.frame = frame;
    }

    public Rect getFrame() {
        return frame;
    }

    public void setView(View view) {
        this.view = view;
        this.view.layout(0, 0, (int) frame.getWidth(), (int) frame.getHeight());
    }

    public void addSubView(BaseView view) {
        if (subViews == null)
            subViews = new ArrayList<>();
        subViews.add(view);
    }
}
