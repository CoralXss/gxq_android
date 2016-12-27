package com.xss.gxq.ui.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xss.gxq.R;
import com.xss.gxq.utils.Constants;

import java.math.BigDecimal;

/**
 *待优化问题：
 *1）原点开始坐标不应该是固定；
 *2）标题所在坐标固定，应该随折线图一起变动；
 *3）一个布局中无法显示两个折线图；？？？
 *4）Y轴坐标只能显示整数；
 *
 */

public class MyLineChartView extends View {
	private float y_span;             //y轴标签的等差值

	private int XPoint;               //起始点
	private int YPoint;
	private String[] XLabels;         //X轴刻度文字
	private String[] YLabels;
	private int XScale;               //X刻度长
	private int YScale;               //Y刻度长	
	private int XLength;
	private int YLength;
	private String[] data;            //显示的折线数据	
	private String title;             //图表的标题

	private int xPadding;             //设置X轴标签与X轴的垂直距离
	private int yPadding;            //设置Y轴标签与Y轴的横向距离
	private int marginLeftOrRight;             //View控件相对父控件的padding

	private int titlePadding;        //标题与图之间的底部间距

	/**
	 * 各种画笔
	 */
	private Paint paint_title;      //图表标题
	private Paint paint_YLabel;     //Y轴标签
	private Paint paint_XLabel;     //X轴标签

	private Paint paint_data;       //折线数据(线)
	private Paint paint_roundRect;  //平均收益率 圆角矩形框
	private Paint paint_average;    //平均收益率

	private Paint paint_xLine;       //X轴线
	private Paint paint_yLine;       //Y轴线

	private Paint paint_gridLine;    //网格线
	private Paint paint_rectStroke;  //网格长方形实心背景


	/**
	 * 自定义属性值
	 *
	 */
	private int backGround;
	private int TitleTextSize;
	private int XTextSize;
	private int YTextSize;
	private int AverageTextSize;

	private int TitleTextColor;
	private int XTextColor;
	private int YTextColor;
	private int AverageTextColor;

	private int LineColor;

	private int RoundRectColor;
	private int RoundRectRadius;


	public void setXYPadding(int xPadding, int yPadding, int titlePadding) {
		this.xPadding = xPadding;
		this.yPadding = yPadding;
		this.titlePadding = titlePadding;
	}

	public void setMarginLeftOrRight(int marginLeftOrRight) {
		this.marginLeftOrRight = marginLeftOrRight;
	}

	public void setXYSLength(int x, int y) {
		this.XLength = x;
		this.YLength = y;
	}

	public void setYSpan(float span) {  //等差值
		this.y_span = span;
	}

	public void setData(String[] XLabels, String[] YLabels, String[] data, String title) {
		this.XLabels = XLabels;
		this.YLabels = YLabels;
		this.data = data;
		this.title = title;

		setPaint_YLabel();
		XPoint = (int) (paint_YLabel.measureText(YLabels[1]) + yPadding + marginLeftOrRight);
		XLength = Constants.SCREEN_WIDTH - XPoint - marginLeftOrRight;  //屏幕宽度-X轴坐标-控件左右间距

		setPaint_title();
		setPaint_XLabel();
		YPoint = (int) (YLength + getTextHeght(paint_title) + titlePadding); //   // + getTextHeght(paint_XLabel) + xPadding
		Log.e("xx", "after坐标：" + XPoint + ", " + YPoint);

		XScale = XLength / XLabels.length;
		YScale = YLength / YLabels.length;
	}

	/**
	 * 获取字体高度
	 * @param paint
	 * @return
	 */
	private int getTextHeght(Paint paint) {
		FontMetrics fm = paint_title.getFontMetrics();
		int txtHeight = (int)(Math.ceil(fm.descent - fm.ascent));
		Log.e("ff", "txtHeight="+ txtHeight);

		return txtHeight;
	}

//	//获取字体宽度
//	private int getTextWidth(Paint paint, String text) {
//		return (int) paint.measureText(text);
//	}

	public int getXPoint() {
		return XPoint;
	}

	public void setXPadding(int yPadding, int paddingLeft) {
		this.yPadding = yPadding;
		this.marginLeftOrRight = paddingLeft;
	}

	public MyLineChartView(Context context) {
		super(context);

	}

	/*
	 * 1.在res/values 文件下定义一个attrs.xml 文件，在该文件中自定义属性文件
	 * 2.在main.xml文件中使用自定义属性必须加上 " xmlns:test ="http://schemas.android.com/apk/res/包名" 
	 * 						 ,test是自定义属性的前缀.
	 */
	public MyLineChartView(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyChartView);

		backGround = typedArray.getResourceId(R.styleable.MyChartView_backGround, -1);

		TitleTextSize = (int) typedArray.getDimension(R.styleable.MyChartView_TitleTextSize, 16);
		XTextSize = (int) typedArray.getDimension(R.styleable.MyChartView_XTextSize, 16);
		YTextSize = (int) typedArray.getDimension(R.styleable.MyChartView_YTextSize, 16);
		AverageTextSize = (int) typedArray.getDimension(R.styleable.MyChartView_AverageTextSize, 16);

		TitleTextColor = typedArray.getColor(R.styleable.MyChartView_TitleTextColor, 0XFFFFFFFF);
		XTextColor = typedArray.getColor(R.styleable.MyChartView_XTextColor, 0XFFFFFFFF);
		YTextColor = typedArray.getColor(R.styleable.MyChartView_YTextColor, 0XFFFFFFFF);
		AverageTextColor = typedArray.getColor(R.styleable.MyChartView_AverageTextColor, 0XFFFFFFFF);

		LineColor = typedArray.getColor(R.styleable.MyChartView_LineColor, 0XFFFFFFFF);

		RoundRectColor = typedArray.getColor(R.styleable.MyChartView_RoundRectColor, 0XFFFFFFFF);
		RoundRectRadius = (int) typedArray.getDimension(R.styleable.MyChartView_RoundRectRadius, 10);

		marginLeftOrRight = (int) typedArray.getDimension(R.styleable.MyChartView_marginLeftOrRight, 0);
		xPadding = (int) typedArray.getDimension(R.styleable.MyChartView_xPadding, 0);
		yPadding = (int) typedArray.getDimension(R.styleable.MyChartView_yPadding, 0);
		titlePadding = (int) typedArray.getDimension(R.styleable.MyChartView_titlePadding, 0);

		if (paint_title == null) {
			setPaint_title();
		}
		if (paint_YLabel == null) {
			setPaint_YLabel();
		}
		if (paint_XLabel == null) {
			setPaint_XLabel();
		}
		if (paint_data == null) {
			setPaint_data();
		}
		if (paint_roundRect == null) {
			setPaint_roundRect();
		}
		if (paint_average == null) {
			setPaint_average();
		}
		if (paint_gridLine == null) {
			setPaint_gridLine();
		}
		if (paint_rectStroke == null) {
			setPaint_rectStroke();
		}

		typedArray.recycle();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int measuredWidth = measureWidth(XLength, widthMeasureSpec);
		int measuredHeight = measureHeight(YPoint, heightMeasureSpec); //YPoint已经包含X轴标签字体和间距高度以及标题高度还有YLength       
		setMeasuredDimension(measuredWidth, measuredHeight); // 记住这句可不能省。

	}

	private int measureWidth(int defaultSize, int measureSpec) {
		// 代码基本类似measureHeight
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		int result = 0;

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			//WRAP_CONTENT的值：Y轴文本size + XLength + 左右padding
			int wid = (int) (defaultSize);
			result = wid;
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	private int measureHeight(int defaultSize, int measureSpec) {
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		// Default size if no limits are specified.
		int result = 0;

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			int hei = (int) (defaultSize + getPaddingTop() + getPaddingBottom() + paint_title.measureText(title) + paint_XLabel.measureText(XLabels[1]));
			//int hei = defaultSize +  + getTextHeght(paint_XLabel) + xPadding + getPaddingTop() + getPaddingBottom();
			result = hei;

			Log.e("hh", "hei = "+hei);
			if (specMode == MeasureSpec.AT_MOST) {

				result =  Math.min(hei, specSize);
			}
		}
		Log.e("hh", "res = " + result);
		return result;
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		//设置Y轴刻度和文字
		setY(canvas);

		//设置偶数列实心矩形背景色
		setStrokeRect(canvas);

		//设置网格
		setGridLine(canvas);

		//设置X轴刻度和文字
		setX(canvas);

		//显示最后一个横坐标显示收益率
		setAverageText(canvas, data[data.length - 1]);

		//设置表的标题
		setTitle(canvas);

		invalidate();
	}


	public void setPaint_title() {
		//设置折线图的标题
		paint_title = new Paint();
		paint_title.setStyle(Paint.Style.STROKE);
		paint_title.setAntiAlias(true); //去锯齿
		paint_title.setColor(TitleTextColor);
		paint_title.setTextSize(TitleTextSize);
	}

	public void setPaint_YLabel() {
		//X轴数值字体颜色
		paint_YLabel = new Paint();
		paint_YLabel.setStyle(Paint.Style.STROKE);
		paint_YLabel.setAntiAlias(true); // 去锯齿
		paint_YLabel.setColor(YTextColor); // 颜色
		paint_YLabel.setTextSize(YTextSize);
	}

	public void setPaint_XLabel() {
		//X轴数值字体颜色
		paint_XLabel = new Paint();
		paint_XLabel.setStyle(Paint.Style.STROKE);
		paint_XLabel.setAntiAlias(true); //去锯齿
		paint_XLabel.setColor(XTextColor); //颜色  
		paint_XLabel.setTextSize(XTextSize);
	}


	public void setPaint_data() {
		// 折线颜色
		// X轴数值字体颜色
		paint_data = new Paint();

		// 渐变
		Shader mShader = new LinearGradient(0, 0, 100, 100,

				new int[] { Color.parseColor("#FFEC8B"), Color.parseColor("#FFEC8B"),
						Color.parseColor("#FFC125"), Color.parseColor("#FFC125"),
						Color.parseColor("#FFA500"), Color.parseColor("#FFA500"),
						Color.parseColor("#FF7F00") },

				null, Shader.TileMode.CLAMP);

		// Shader.TileMode三种模式

		// REPEAT:沿着渐变方向循环重复

		// CLAMP:如果在预先定义的范围外画的话，就重复边界的颜色

		// MIRROR:与REPEAT一样都是循环重复，但这个会对称重复

		paint_data.setShader(mShader);// 用Shader中定义定义的颜色来话

		paint_data.setStyle(Paint.Style.STROKE);
		paint_data.setStrokeWidth(5); // 设置线条粗细
		paint_data.setAntiAlias(true); // 去锯齿
		// linePaint.setColor(Color.parseColor("#FD6138")); //颜色
		// Color.parseColor("#FD6138")
		paint_data.setTextSize(16);
	}

	public void setPaint_roundRect() {
		paint_roundRect = new Paint();
		paint_roundRect.setStyle(Paint.Style.FILL);
		paint_roundRect.setAntiAlias(true);
		paint_roundRect.setColor(RoundRectColor);
	}

	public void setPaint_average() {
		paint_average = new Paint();
		paint_average.setAntiAlias(true);
		paint_average.setColor(AverageTextColor);
		paint_average.setTextSize(AverageTextSize);
	}


	public void setPaint_xLine() {

	}

	public void setPaint_yLine() {

	}


	public void setPaint_gridLine() {
		//设置网格线
		paint_gridLine = new Paint();
		paint_gridLine.setStyle(Paint.Style.STROKE);
		paint_gridLine.setAntiAlias(true); //去锯齿		
		paint_gridLine.setColor(Color.parseColor("#DDDDDD")); //颜色
	}

	public void setPaint_rectStroke() {
		paint_rectStroke = new Paint();
		paint_rectStroke.setStyle(Paint.Style.FILL);
		paint_rectStroke.setAntiAlias(true);
		paint_rectStroke.setColor(Color.parseColor("#FFF8DC"));
	}



	/**
	 * 设置表的标题
	 * @param canvas
	 */
	private void setTitle(Canvas canvas) {
		canvas.drawText(title, XPoint, (YPoint - YLength) - titlePadding, paint_title);  //getTextHeght(paint_title)
	}

	/**
	 * 设置X轴刻度和文字
	 * @param canvas
	 */
	protected void setX(Canvas canvas) {
		canvas.drawLine(XPoint, YPoint, XPoint + XLength, YPoint, paint_gridLine);
		for (int j = 0; j < XLabels.length; j++) {
			// 画刻度
			// canvas.drawLine(XPoint + j * XScale, YPoint, XPoint + j * XScale, YPoint - 5, paint);

			// 画X轴刻度文字
			try {
				canvas.drawText(XLabels[j], XPoint + j * XScale - 5, YPoint + getTextHeght(paint_XLabel) + xPadding, paint_XLabel);

				// 画折线数据
				if (YCoord2(data[j]) != -999 && j < XLabels.length - 1 && YCoord2(data[j + 1]) != -999) { // 保证数据有效
					// 前一个数据的坐标 同 后一个数据的坐标 画一条直线
					Log.e("hh", data[j]);
					canvas.drawLine(XPoint + j * XScale, YCoord2(data[j]),
							XPoint + (j + 1) * XScale, YCoord2(data[j + 1]),
							paint_data);

					// 每一个点画一个圆圈
					// canvas.drawCircle(XPoint + j * XScale, YCoord2(data[j]),  2, paint);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			canvas.drawCircle(XPoint + (XLabels.length - 1) * XScale, YCoord2(data[XLabels.length - 1]), 5, paint_data);
		}

		// 画X轴箭头
		canvas.drawLine(XPoint + XLength, YPoint, XPoint + XLength - 5, YPoint - 5, paint_gridLine);
		canvas.drawLine(XPoint + XLength, YPoint, XPoint + XLength - 5, YPoint + 5, paint_gridLine);
	}

	/**
	 * 设置Y轴刻度和文字
	 * @param canvas
	 */
	private void setY(Canvas canvas) {
		// 设置Y轴
		canvas.drawLine(XPoint, YPoint - YLength, XPoint, YPoint, paint_gridLine);
		int i = 0;
		for (i = 0; i < YLabels.length; i++) {
			// 画刻度
			// canvas.drawLine(XPoint, YPoint - i * YScale, XPoint + 5, YPoint - i * YScale, paint);

			// 画Y轴刻度文字
			try {
				//	canvas.drawText(YLabels[i], XPoint - 50, YPoint - i * YScale + 5, paint_YLabel);

				canvas.drawText(YLabels[i], XPoint - paint_YLabel.measureText(YLabels[1]) - yPadding, YPoint - i * YScale + 5, paint_YLabel);

				// canvas.drawText(YLabels[i], XPoint - 50, YCoord2(YLabels[i]),
				// paint); //设置小数类型文本
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 画Y轴箭头
		canvas.drawLine(XPoint, YPoint - YLength, XPoint + 5, YPoint - YLength + 5, paint_gridLine);
		canvas.drawLine(XPoint, YPoint - YLength, XPoint - 5, YPoint - YLength + 5, paint_gridLine);

	}

	/**
	 * 偶数列显示实心矩形 
	 * @param canvas
	 */
	protected void setStrokeRect(Canvas canvas) {
		//将偶数列 化成设置成实心矩形
		for (int k = 0; k < XLabels.length - 1; k+=2) {
			canvas.drawRect(XPoint + (k + 1) * XScale, YPoint - YLength, XPoint + (k + 2) * XScale, YPoint, paint_rectStroke);
		}
	}

	/**
	 * 显示网格
	 * @param canvas
	 */
	protected void setGridLine(Canvas canvas) {
		for (int i = 1; i < XLabels.length; i++) {
			canvas.drawLine(XPoint+i*XScale, YPoint-YLength, XPoint+i*XScale, YPoint, paint_gridLine);
		}

		for (int i = 1; i < YLabels.length; i++) {
			canvas.drawLine(XPoint, YPoint-i*YScale, XPoint+XLength, YPoint-i*YScale, paint_gridLine);
		}
	}

	/**
	 * 最后一个横坐标显示收益率
	 * @param canvas
	 * @param txt  平均收益率
	 */
	protected void setAverageText(Canvas canvas, String txt) {
		float x = XPoint + (XLabels.length - 1) * XScale;
		float y = YCoord2(data[XLabels.length - 1]);
//		RectF rect = new RectF((int)(x - 40), (int)(y - 60), (int)(x + 40), (int)(y - 20));
//		canvas.drawRoundRect(rect, 20, 20, paint_roundRect);		
//		canvas.drawText(txt, x - 20, y - 35, paint_average);	

		int txt_width = (int) paint_average.measureText(txt);
		int txt_size = (int) paint_average.getTextSize();

//		//左上右下  (居中)
//		RectF rect = new RectF((int)(x-30-txt_width/2), (int)(y - 60), (int)(x + 30+txt_width/2), (int)(y - 20));
//		canvas.drawRoundRect(rect, 20, 20, paint_roundRect);		
//		canvas.drawText(txt, x - txt_width/2, y - 20-12, paint_average);
		
/*		//左上右下  (居左)
		int rect_width = 40+txt_width;
		int rect_height = 35+txt_size;		
		RectF rect = new RectF((int)(x-rect_width), (int)(y - rect_height - 20), (int)(x), (int)(y - 20));
		canvas.drawRoundRect(rect, 15, 15, paint_roundRect);	
		//使文本居中
		canvas.drawText(txt, x - rect_width/2 - txt_width/2, y - rect_height/2 - 20+txt_size/3, paint_average);
*/

		//左上右下  (居左)
		int radius = 15;
		int rect_width = (int) paint_average.measureText(txt) + radius;
		int rect_height = getTextHeght(paint_average) + radius;
		RectF rect = new RectF((int)(x-rect_width), (int)(y - rect_height - 20), (int)(x), (int)(y - 20));
		canvas.drawRoundRect(rect, RoundRectRadius, RoundRectRadius, paint_roundRect);
		//使文本居中
		canvas.drawText(txt, x - rect_width/2 - txt_width/2, y - rect_height/2 - 20+txt_size/3, paint_average);
	}

	/**
	 * 计算Y轴坐标
	 * @param y  折线数据 
	 * @return
	 */
	private float YCoord2(String y) {
		float y_current = Float.parseFloat(y);
		float yLabel1 = Float.parseFloat(YLabels[1]);

		BigDecimal b1 = null;
		BigDecimal b2 = null;
		if(!"".equals(y)) {
			b1 = new BigDecimal((y_current - (yLabel1 - y_span))+"");   //0.19 表示格之间的数值差
		}
		if (y_span != 0) {
			b2 = new BigDecimal(y_span+"");
			System.out.println("test5 = " + b1.divide(b2, 3).doubleValue() + y_span);
			try {
				return YPoint - b1.divide(b2, 3).floatValue() * YScale;  //保留三位小数
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -999;
	}

	public int dip2px(Context context, float dipValue)
	{
		float m=context.getResources().getDisplayMetrics().density ;
		return (int)(dipValue * m + 0.5f) ;
	}

	public int px2dip(Context context, float pxValue)
	{
		float m=context.getResources().getDisplayMetrics().density ;
		return (int)(pxValue / m + 0.5f) ;
	}

}
