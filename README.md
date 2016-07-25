# StereoView

--The idea is from weibo.com

**This is the original animation effects .**

![](https://camo.githubusercontent.com/dc86ed6f966895ef1e343efc5cc7fd0469ec33c1/687474703a2f2f696d672e626c6f672e6373646e2e6e65742f3230313630373135313535343532303530)

**This is what we achieve animation effects.**

![](https://camo.githubusercontent.com/d0e30babb28951722717fa32f9d273b7eec12232/687474703a2f2f696d672e626c6f672e6373646e2e6e65742f3230313630373135313535373236373837)

**we can infinite loop rolling**

![](https://camo.githubusercontent.com/043b4cf0ad7c9bee2dcdb500bb26f81af3d903c6/687474703a2f2f696d672e626c6f672e6373646e2e6e65742f3230313630373135313535383132303037)


**Of course,on the basis of the original,I add the other animation effects.
As you see.**
![](https://camo.githubusercontent.com/55cb4f3363c24788d7398c81047cb6a506eecceb/687474703a2f2f696d672e626c6f672e6373646e2e6e65742f3230313630373135313631333532363233)

**it extends ViewGroup,of course we can contain any view like imageview**

![](https://camo.githubusercontent.com/69d48f79bb080c5c196ff99211e2d09522a6bb81/687474703a2f2f696d672e626c6f672e6373646e2e6e65742f3230313630373135313833393439323739)

# How to use it ? #
---
just like use ViewGroup.You can learn from it by demo.

# Additional Options #
---
to set the first show item
> setStartScreen(int startScreen) ： @param startScreen (0,getChildCount-1)

to set the move resistance
> setResistance(float resistance) ：  @param resistance (0,...)

to set the interpolator
> setInterpolator(Interpolator mInterpolator) ： 

to set the degree of tow item which are rolling
> setAngle(float mAngle)： [0f,180f]

to set whether to use 3D animation effects.
> setCan3D(boolean can3D) 

turn to specified position.
> setItem(int itemId) :  @param itemId [0,getChildCount-1]

turn to previous item
> toPre() 

turn to next item
> toNext() 

define interface

![](https://camo.githubusercontent.com/8abcd140df64926030c3e3aee12abc56a453a3fd/687474703a2f2f696d672e626c6f672e6373646e2e6e65742f3230313630373135313831363139343434)

# Defects #
---
1.to show best,you must ensure the StereoView must have threee child at least.

2.to show best,you must ensure the method's ( setStartScreen(int startScreen) ) parameter are correct.


---



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

自定义的方法
----


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


缺陷说明
----

目前容器的item数量需要大于等于3，小于3个滑动时会些问题。设置的最开始展示的item位置不能是第一个或者最后一个，这么做是为了保证第1个或者最后一个被隐藏，从而保证最开始向上滑动或者向下滑动时的正常。
