# StereoView
Android 3D立体无限旋转滚动容器

相应的博文 http://blog.csdn.net/Mr_immortalZ/article/details/51918560

**原效果**

![这里写图片描述](http://img.blog.csdn.net/20160715155452050)

**我们实现的效果：**

（为了更加可定制化，我在原图基础上新增了新的效果）

![这里写图片描述](http://img.blog.csdn.net/20160715155726787)

可以快速滚动，并且无限循环

![这里写图片描述](http://img.blog.csdn.net/20160715155812007)

这个是对一些参数的进行设定

![这里写图片描述](http://img.blog.csdn.net/20160715161352623)

对图片的包裹效果

![这里写图片描述](http://img.blog.csdn.net/20160715183949279)

因为本身继承自ViewGroup，所以基本控件都是可以包裹的

**自定义的方法**

**setStartScreen(int startScreen)** ：设置第一页展示的页面 @param startScreen (0,getChildCount-1)

**setResistance(float resistance)** ： 设置滑动阻力  @param resistance (0,...)

**setInterpolator(Interpolator mInterpolator)** ： 设置滚动时interpolator插补器

**setAngle(float mAngle)**：设置滚动时两个item的夹角度数 [0f,180f]

**setCan3D(boolean can3D)** : 是否开启3D效果

**setItem(int itemId)** :  跳转到指定的item @param itemId [0,getChildCount-1]

**toPre()** : 上一页

**toNext()** ： 下一页

**定义的回调接口**

![这里写图片描述](http://img.blog.csdn.net/20160715181619444)


** 缺陷说明**

目前容器的item数量需要大于等于3，小于3个滑动时会些问题。设置的最开始展示的item位置要是第2个，这么做是为了保证第1个被隐藏，从而导致最开始向上滑动时的正常。
