package com.wanhong.controller;

import com.wanhong.common.errorcode.BusinessCode;
import com.wanhong.dao.FileInfoDao;
import com.wanhong.domain.DataInfo;
import com.wanhong.domain.FileInfo;
import com.wanhong.domain.ResultJson;
import com.wanhong.domain.common.Page;
import com.wanhong.domain.vo.FileInfoVo;
import com.wanhong.service.FileService;
import com.wanhong.util.BeanUtil;
import com.wanhong.util.FastjsonUtil;
import com.wanhong.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangmeng247
 * @date 2018-02-10 13:56
 */
@Controller
@RequestMapping("/function/upload")
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    FileService fileService;

    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public ResultJson<FileInfoVo> uploadCServicePic(MultipartFile uploadFile, HttpServletRequest request){
        ResultJson<FileInfoVo> resultJson = new ResultJson<>(BusinessCode.UNKNOWN_ERROR);
        Map<String, Object> ret = new HashMap<>();
        try {
            String originalFilename = uploadFile.getOriginalFilename();
            String fileContentType = uploadFile.getContentType();
            long fileSize = uploadFile.getSize();

            logger.info("上传处方原始文件原名：{}", originalFilename);
            logger.info("上传处方原始文件类型：{}", fileContentType);
            logger.info("上传的文件大小：{}",fileSize);
            if (uploadFile == null || uploadFile.isEmpty() || StringUtil.hasBlank(originalFilename,fileContentType) ) {
                resultJson = new ResultJson<>(BusinessCode.GET_FILE_ERROR);
            } else if (fileSize > 1024 * 1024 * 1024) {
                resultJson = new ResultJson<>(BusinessCode.FILE_SIZE_ERROR);
            } else {
//                String realPath = request.getSession().getServletContext().getRealPath("/upload");
                String realPath = "";
                ResourceBundle resource = ResourceBundle.getBundle("common");//
                realPath = resource.getString("uploadFilePath");
                logger.info("uploadFilePath = " + realPath);
                realPath = createPath(realPath);
                String uuid = UUID.randomUUID().toString();
                String fileName = "";
                if (originalFilename.lastIndexOf(".")>=0){
                    fileName = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
                }
                fileName = uuid+fileName;
                File picFile =new File(realPath,fileName);

                FileUtils.copyInputStreamToFile(uploadFile.getInputStream(),picFile);
                ret.put("desc", "成功了。");
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileRealName(originalFilename);
                fileInfo.setFilePath(picFile.getAbsolutePath());
                fileInfo.setFileName(fileName);
                fileInfo.setFileType(fileContentType);
                fileInfo.setFileSize(fileSize);
                Integer res = fileService.saveFileInfo(fileInfo);
                if (res >0){
                    FileInfoVo fileInfoVo = new FileInfoVo();
                    BeanUtil.copyProperties(fileInfo,fileInfoVo);
                    resultJson = new ResultJson<>(BusinessCode.SUCCESS,fileInfoVo);
                }
            }
        } catch (Exception e) {
            logger.error("保存上传的图片失败", e);
            resultJson = new ResultJson<>(BusinessCode.FILE_UPLOAD_ERROR);
        }
        String json= FastjsonUtil.objectToJson(ret);
        logger.info("客服上传处方信息："+json);
        return resultJson;
    }

    //根据年月日 创建上传文件目录
    private String createPath(String basePath){
        Calendar date=Calendar.getInstance();
        SimpleDateFormat format1=new SimpleDateFormat( "yyyy ");
        SimpleDateFormat format2=new SimpleDateFormat( "MM ");
        SimpleDateFormat format3=new SimpleDateFormat( "dd ");
        String name1=format1.format(date.getTime());
        String name2=format2.format(date.getTime());
        String name3=format3.format(date.getTime());
        File file1=new File( basePath+"/"+name1);
        File file2=new File( basePath+"/"+name1+"/"+name2);
        File file3=new File( basePath+"/"+name1+"/"+name2+"/"+name3);
        if (!file1.exists()){
            file1.mkdir();
        }
        if (!file2.exists()){
            file2.mkdir();
        }
        if (!file3.exists()){
            file3.mkdir();
        }
        basePath = basePath+"/"+name1+"/"+name2+"/"+name3;
        return basePath;
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody String ExceptionHandler(Exception exceededException) {
        Map<String, Object> jsonResult = new HashMap<>();
        if ((exceededException.getCause() instanceof MaxUploadSizeExceededException)) {
            jsonResult.put("code","0003");
            jsonResult.put("msg","文件过大");
        }else {
            jsonResult.put("code","0000");
        }
        String str = FastjsonUtil.objectToJson(jsonResult);
        return str;
    }


    @RequestMapping(value="/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @RequestParam("fileId") Long fileId)throws Exception {
        FileInfo fileInfoQuery = new FileInfo();
        fileInfoQuery.setFileId(fileId);
        FileInfo fileInfo = fileService.getFileInfoById(fileInfoQuery);
        String fileName = fileInfo.getFileRealName();
        String filePath = fileInfo.getFilePath();
        //下载文件路径
//        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }


    @RequestMapping("/downLoadFile")
    @ResponseBody
    public void downLoadFile(Long fileId, HttpServletRequest request, HttpServletResponse response){
        try{
            FileInfo fileInfoQuery = new FileInfo();
            fileInfoQuery.setFileId(fileId);
            FileInfo fileInfo = fileService.getFileInfoById(fileInfoQuery);
            String fileName = fileInfo.getFileRealName();
            String filePath = fileInfo.getFilePath();
            if(logger.isDebugEnabled()){
                logger.debug("待下载文件的名称："+fileName);
            }
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try{
                if(logger.isDebugEnabled()){
                    logger.debug("创建输入流读取文件...");
                }
                //获取输入流
                bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
                //获取输出流
                if(logger.isDebugEnabled()){
                    logger.debug("创建输出流下载文件...");
                }
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
                bos = new BufferedOutputStream(response.getOutputStream());
                //定义缓冲池大小，开始读写
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                //刷新缓冲，写出
                bos.flush();
                if(logger.isDebugEnabled()){
                    logger.debug("文件下载成功。");
                }
            }catch(Exception e){
                logger.error("文件下载失败"+e.getMessage());
            }finally{
                //关闭流
                if(bis != null){
                    try {
                        bis.close();
                    } catch (IOException e) {
                        logger.error("关闭输入流失败，"+e.getMessage());
                        e.printStackTrace();
                    }
                }
                if(bis != null){
                    try {
                        bos.close();
                    } catch (IOException e) {
                        logger.error("关闭输出流失败，"+e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
