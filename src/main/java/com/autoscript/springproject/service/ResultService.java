package com.autoscript.springproject.service;

import com.autoscript.springproject.api.ResultRepository;
import com.autoscript.springproject.domain.Result;
import com.autoscript.springproject.domain.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * @author Chen Yinqi
 */
@RestController
public class ResultService {
    /**
     * @param commandStr command
     * @param folderPath absolute path of folder
     * @param fileName fileName
     */

    @Autowired
    ResultRepository resultRepository;

    public String exeCmd(String commandStr, String folderPath, String fileName, String toSave) {
        String result = "";
        try
        {
            System.out.println(commandStr);
            Process process = Runtime.getRuntime ().exec (commandStr);
            SequenceInputStream sis = new SequenceInputStream (process.getInputStream (), process.getErrorStream ());
            InputStreamReader isr = new InputStreamReader (sis, "gbk");
            BufferedReader br = new BufferedReader (isr);
            // Construct a write flow and absolute path
            String savePath = toSave;
            File filePath = new File(savePath);

            if (!filePath.exists()){
                filePath.mkdirs();
            }

            FileWriter fw = new FileWriter(filePath+"\\result.txt");
            // read
            String line = null;
            while (null != ( line = br.readLine () ))
            {
                // write
                fw.write(line + "\n");
//                System.out.println (line);
                result += (line+"\n");

            }
            // Refresh flow
            fw.flush();
            // Close output flow
            fw.close();
            // Close Process
            process.getOutputStream().close();
            process.destroy ();
            br.close ();
            isr.close ();
        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }
        return result;
    }

    /**
     *  @param file absolute path of file
     * @param lang language: Java, Python, C
     */
    public String compile(String file, String lang, String toSave, String para) {
        String result;
        String commandStr = "";
        // The absolute path of folder
        String folderPath = "";
        // The absolute path of complied class
        String fileClassPath = "";
        // The absolute path of running class
        String fileRunPath = "";
        String[] s = file.split("\\\\");
        //Pick up the fileName
        String fileName = s[s.length-1].split("\\.")[0];
        // The absolute path of folder
        for (int i = 0; i < s.length-1; i++){
            folderPath += (s[i]+"\\");
        }
        switch(lang){
            case "Python":
                commandStr = "python "+file +" "+para;
                break;
            case "Java":
                // The absolute path of complied class
                fileClassPath = (folderPath + "\\" + fileName + ".class");
                File fileClass = new File(fileClassPath);
                // Delete the old one
                if(fileClass.exists()&&fileClass.isFile()) {
                    fileClass.delete();
                }
                // The absolute path of running class
                fileRunPath = folderPath + " " + fileName;

                //Compile first
                commandStr = "javac "+file;
                result = this.exeCmd(commandStr,folderPath,fileName,toSave);
                //Judge whether the compiled class occurs or not
                if(!fileClass.exists()){
                    return result;
                }
                //Execute class
                commandStr = "java -cp "+fileRunPath + " " + para;
                break;
            case "C":
                // The absolute path of complied execution
                fileClassPath = (folderPath + "" + fileName + ".exe");
                File fileExe = new File(fileClassPath);
                // Delete the old one
                if(fileExe.exists()&&fileExe.isFile()) {
                    fileExe.delete();
                }
                // Compile first
                commandStr = "gcc -g "+file+" -o "+fileClassPath;
                result = this.exeCmd(commandStr,folderPath,fileName, toSave);
                // Judge whether the compiled class occurs or not
                if(!fileExe.exists()){
                    return result;
                }
                //Execute class exe
                commandStr = "\""+fileClassPath+"\"" + " " + para;
                break;
            default:
                break;
        }
        System.out.println("run: "+commandStr);
        result = this.exeCmd(commandStr, folderPath,fileName, toSave);
        return result;
    }
    //constrains between designer and result
    @Transactional
    public void linkToDesignerAndResult(long did, long rid){
        resultRepository.insertToLinkDesigner(did, rid);
    }
    //constrains between user and result
    @Transactional
    public void linkToUserAndResult(long uid, long rid){
        resultRepository.insertToLinkUser(uid, rid);
    }
    //get next id
    public long getNext(){
        return resultRepository.getNext();
    }
    //find by designer id
    public List<Result> findByDesignerId(long id){
        return resultRepository.findByDesignerId(id);
    }
    //find result by userid
    public List<Result> findByUserId(long id){
        return resultRepository.findByUserId(id);
    }
    //add result
    public Result addResult(Result result){
        return resultRepository.save(result);
    }
    //find all result
    public List<Result> findAll(){
        return resultRepository.findAll();
    }
    //find by resultid
    public Result findById(long resultid) {
        return resultRepository.findById(resultid).get();
    }
}