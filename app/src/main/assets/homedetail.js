//根据class名称获取div数组
function getClass(parent,sClass)
{
    var aEle=parent.getElementsByTagName('div');
    var aResult=[];
    var i=0;
    for(i<0; i<aEle.length; i++) {
        if(aEle[i].className==sClass)
        {
            aResult.push(aEle[i]);
        }
    };
    return aResult;
}


//更改特定div的css属性
function hideOther()
{
    getClass(document,'nav-sides')[0].style.display='none';
    getClass(document,'side-bar')[0].style.display='none';
    getClass(document,'area-main')[0].style.display='none';
    getClass(document,'home-foot')[0].style.display='none';
    getClass(document,'enter')[0].style.display='none';
    getClass(document,'crumb')[0].style.display='none';
    getClass(document,'date-tab clearfix')[0].style.display='none';
    document.getElementById('id_sidebar').style.display='none';
    document.getElementById('top_nav').style.display='none';
    document.getElementById('fix-personal').style.display='none';
    document.getElementById('waterlogo').style.display='none';

    getClass(document,'wrap')[0].style.minWidth=0;
    getClass(document,'game')[0].style.paddingTop=0;
}